package fileManager;

import java.util.ArrayList;


public class ExportManager implements Exporter {

	private Exporter exporter;

	public ExportManager(Exporter exporter) {
		this.exporter = exporter;
	}
	@Override
	public void exportData(ArrayList<Object> bundle, String path) {
		exporter.exportData(bundle, path);
		
	}

}
