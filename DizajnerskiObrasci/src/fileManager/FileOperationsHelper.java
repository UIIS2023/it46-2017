package fileManager;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class FileOperationsHelper {
	private static Pattern pattern;
	private static Matcher matcher;
	private static final String LOG_PATTERN = "([^\\s]+(\\.(?i)(log))$)";;
	private static final String DRAWING_PATTERN = "([^\\s]+(\\.(?i)(drwg))$)";;

	/**
	 * Will open JFileChooser and return absolute path if file selected Method will
	 * do extended check for file extension, if no extension is specified it will
	 * append proper extension, will ask for overwrite approve
	 * 
	 * @return String
	 */
	public static String showFileDialogSave(String filter) {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()) {

			/**
			 * 
			 */
			private static final long serialVersionUID = -4056709857933644053L;

			@Override
			public void approveSelection() {
				if (getSelectedFile().exists()) {
					int n = JOptionPane.showConfirmDialog(this, "Do You Want to Overwrite File?", "Confirm Overwrite",
							JOptionPane.YES_NO_OPTION);

					if (n == JOptionPane.YES_OPTION)
						super.approveSelection();

				} else
					super.approveSelection();
			}

		};
		jfc.setFileFilter(new FileNameExtensionFilter("*." + filter, filter));

		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			pattern = Pattern.compile((filter.toLowerCase().equals("log")) ? LOG_PATTERN : DRAWING_PATTERN);
			matcher = pattern.matcher(selectedFile.getAbsolutePath());
			return matcher.matches() ? selectedFile.getAbsolutePath() : selectedFile.getAbsolutePath() + "." + filter;
		}
		return null;
	}

	/**
	 * Will open JFileChooser and return absolute path if file selected Method will
	 * do extended check for file extension, if no extension is specified it will
	 * append proper extension
	 * 
	 * @return String
	 */
	public static String showFileDialogOpen(String filter) {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setFileFilter(new FileNameExtensionFilter("*." + filter, filter));

		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			pattern = Pattern.compile((filter.toLowerCase().equals("log")) ? LOG_PATTERN : DRAWING_PATTERN);
			matcher = pattern.matcher(selectedFile.getAbsolutePath());
			return matcher.matches() ? selectedFile.getAbsolutePath() : selectedFile.getAbsolutePath() + "." + filter;
		}
		return null;
	}
}
