package fileManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;



public class LoadLogFromFile implements Importer {

	@Override
	public ArrayList<Object> importData(String path) {
		if (path == null)
			return null;
		ArrayList<Object> bundle = new ArrayList<Object>();
		ArrayList<String> logLines = new ArrayList<String>();

		try (Stream<String> stream = Files.lines(Paths.get(path))) {
			stream.forEach(logLines::add); // Fancy new Java 8 way
			bundle.add(logLines);
			Logger.getInstance().log("Log from path " + path + " has been imported successfully", true, true);
			Logger.getInstance().log("Detected " + logLines.size() + " log lines.", false, true);
		} catch (IOException e) {
			String msg = "Error while importing log from path " + path + " , Error message : " + e.getMessage();
			e.printStackTrace();
			DialogsHelper.showErrorMessage(msg);
			Logger.getInstance().log(msg, true, true);
		}
		return bundle;
	}

}
