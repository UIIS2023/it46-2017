package fileManager;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



public class SerializeShapesToFile implements Exporter {

	@Override
	public void exportData(ArrayList<Object> objects, String path) {
		if (path == null)
			return;
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {

			oos.writeObject(objects);
			Logger.getInstance().log("All shapes have been exported to path " + path, true, true);

		} catch (Exception ex) {
			String msg = "Error while exporting shapes, error message:" + ex.getMessage();
			ex.printStackTrace();
			DialogsHelper.showErrorMessage(msg);
			Logger.getInstance().log(msg, true, true);
		}
		
	}

}
