package fileManager;

import java.io.Serializable;
import java.util.ArrayList;

public class LoggerModel implements Serializable {
	
	private static final long serialVersionUID = 5123950954921580008L;
	private ArrayList<String> logLines = new ArrayList<String>();

	/**
	 * Will add given line to log
	 * @param s
	 */
	public void add(String s) {
		logLines.add(s);
	}
	
	/**
	 * Will remove line from log
	 * @param s
	 * @return
	 */
	public boolean remove(String s) {
		return logLines.remove(s);
	}
	
	/**
	 * Will return last element of list
	 * @return
	 */
	public String peek() {
		return logLines.get(logLines.size()-1);
	}
	
	public ArrayList<String> getLogLines() {
		return logLines;
	}

	public void setLogLines(ArrayList<String> logLines) {
		this.logLines = logLines;
		System.out.println(logLines.size());
	}
}
