package fileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;



public class SaveLogToFile implements Exporter {
	
	
	public ArrayList<String> getObjects(ArrayList<Object> objects) {
		ArrayList<String>stringArray = new ArrayList<String>();
		for(Object o :objects) {
			String s;
			s=o.toString();
			stringArray.add(s);
			
		}
		return stringArray;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportData(ArrayList<Object> objects, String path) {
		if (path == null)
			return;
		ArrayList<String> objects2 = new ArrayList<String>();
		objects2 =getObjects(objects);
		File logFile = new File(path);
		if(logFile.exists()) {
			logFile.delete();
			logFile = new File(path);
		}
		try {
			logFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		Path pathObj = Paths.get(path);
		try {
			for (String logLine :  objects2) {
				logLine += "\n";
				byte[] strToBytes = logLine.getBytes();
				try {
					Files.write(pathObj, strToBytes, StandardOpenOption.APPEND);
				} catch (IOException e) {
					String msg = "Error while exporting log file, error message: " + e.getMessage();
					DialogsHelper.showErrorMessage(msg);
					Logger.getInstance().log(msg, true, true);
					e.printStackTrace();
				}
			}
			Logger.getInstance().log("Log has been exported to " + path, true, true);
		} catch (Exception e) {
			String msg = "Error while exporting log file, error message: " + e.getMessage();
			DialogsHelper.showErrorMessage(msg);
			Logger.getInstance().log(msg, true, true);
			e.printStackTrace();
		}

		
	}

}
