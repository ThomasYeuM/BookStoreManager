package view;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class BillManagementView extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public BillManagementView() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bill manage");
		lblNewLabel.setBounds(125, 88, 198, 116);
		add(lblNewLabel);
		
	}

}
