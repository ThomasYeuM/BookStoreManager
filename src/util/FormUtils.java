package util;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormUtils {
	public static boolean ValidateForm(JPanel panel) {
		ArrayList<JTextField> textFields = new ArrayList<>();

		// Iterate through all components in the panel
		for (Component component : panel.getComponents()) {
			// Check if the component is a JTextField
			if (component instanceof JTextField) {
				JTextField textField = (JTextField) component;
				textFields.add(textField);
			}
		}

		// Iterate through the collected text fields and check for empty values
		for (JTextField textField : textFields) {
			if (textField.getText().trim().isEmpty()) {
				return false; // Form validation failed
			}
		}
		// If all fields are filled, form validation passed
		return true;
	}
	
	
	
}
