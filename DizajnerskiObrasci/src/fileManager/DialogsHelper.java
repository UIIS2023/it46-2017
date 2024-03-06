package fileManager;

import javax.swing.JOptionPane;

public class DialogsHelper {
	public static void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showInformationMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void showPlainsMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.PLAIN_MESSAGE);
	}
	
	public static boolean askUserToConfirm(String question) {
		int response = JOptionPane.showConfirmDialog(null, question, "Confirm action", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		switch (response) {
		case JOptionPane.NO_OPTION:
			return false;
		case JOptionPane.YES_OPTION:
			return true;
		case JOptionPane.CLOSED_OPTION:
			return false;
		default:
			return false;
		}
	}
		
		public static String getUserInput(String message, String title, int icon) {
			return JOptionPane.showInputDialog(null, message, title, icon);
		}
}
