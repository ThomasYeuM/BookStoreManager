package util;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

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
	public static void resetForm(JPanel inputPanel) {
		List<JTextField> jtfs = new ArrayList<>();
		collectTextFields(inputPanel, jtfs);
		jtfs.forEach(jtf -> jtf.setText(""));
	}
	private static void collectTextFields(JPanel panel, List<JTextField> jtfs) {
		Component[] components = panel.getComponents();
		for (Component component : components) {
			if (component instanceof JTextField) {
				jtfs.add((JTextField) component);
			} else if (component instanceof JPanel) {
				collectTextFields((JPanel) component, jtfs);
			}
		}
	}
	
}
