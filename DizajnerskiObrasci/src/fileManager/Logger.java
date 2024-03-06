package fileManager;

import javax.swing.DefaultListModel;

public class Logger {
	private static Logger instance;
	private LoggerModel loggerModel = new LoggerModel();
	private DefaultListModel<String> dlmLogger = new DefaultListModel<String>();

	private Logger() {
	}

	/**
	 * Will add given string to logger model (List)
	 * 
	 * @param action
	 * @param s
	 * @param toConsole
	 *            if true it will print to console as well
	 */
	public void log(String action, String s, boolean toConsole) {
		loggerModel.add(action.toUpperCase() + "_" + s);
		dlmLogger.addElement(loggerModel.peek());
		LoggerView.getLstLogger().ensureIndexIsVisible(dlmLogger.getSize() - 1); // Scroll to bottom of log
																					// automatically
		if (toConsole)
			System.out.println(loggerModel.peek());
	}

	/**
	 * Will add given string to logger model (List) with [INFO] prefix
	 * 
	 * @param action
	 * @param s
	 * @param toConsole
	 */
	public void log(String action, String s, boolean toConsole, boolean info) {
		if (info) {
			loggerModel.add("[INFO] " + action.toUpperCase() + "_" + s);
		} else {
			loggerModel.add(action.toUpperCase() + "_" + s);
		}
		dlmLogger.addElement(loggerModel.peek());
		LoggerView.getLstLogger().ensureIndexIsVisible(dlmLogger.getSize() - 1); // Scroll to bottom of log
																					// automatically
		if (toConsole)
			System.out.println(loggerModel.peek());
	}

	/**
	 * Will add given string to logger model (List), will also add shape id to
	 * output
	 * 
	 * @param action
	 * @param shapeId
	 * @param s
	 * @param toConsole
	 */
	public void log(String action, int shapeId, String s, boolean toConsole) {
		loggerModel.add(action.toUpperCase() + "_sid=" + shapeId + "_" + s);
		dlmLogger.addElement(loggerModel.peek());
		if (LoggerView.getLstLogger() != null) {
			LoggerView.getLstLogger().ensureIndexIsVisible(dlmLogger.getSize() - 1); // Scroll to bottom of log
			// automatically
		}

		if (toConsole)
			System.out.println(loggerModel.peek());
	}

	/**
	 * Will add given string to logger model (List), if info is true it will add
	 * [INFO] prefix
	 * 
	 * @param s
	 * @param toConsole
	 */
	public void log(String s, boolean toConsole, boolean info) {
		if (info) {
			s = "[INFO] " + s;
		}
		loggerModel.add(s);
		dlmLogger.addElement(loggerModel.peek());
//		LoggerView.getLstLogger().ensureIndexIsVisible(dlmLogger.getSize() - 1); 
		// Scroll to bottom of log
																					// automatically
		if (toConsole)
			System.out.println(loggerModel.peek());
	}

	/**
	 * Return Thread safe singleton object also using Lazy Loading
	 * 
	 * @return Logger
	 */
	public static Logger getInstance() {
		if (instance == null) {
			synchronized (Logger.class) {
				if (instance == null) {
					instance = new Logger();
				}
			}
		}
		return instance;
	}

	public DefaultListModel<String> getDlmLogger() {
		return dlmLogger;
	}

	public LoggerModel getLoggerModel() {
		return loggerModel;
	}
}
