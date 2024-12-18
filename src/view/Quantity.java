package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class Quantity extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private int quantity = -1; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quantity frame = new Quantity();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Quantity() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Đóng cửa sổ thay vì tắt chương trình
		setBounds(100, 100, 343, 157);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(171, 21, 151, 30);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nhập số lượng:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 21, 151, 30);
		contentPane.add(lblNewLabel);

		btnNewButton = new JButton("Xác nhận");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int qty = Integer.parseInt(textField.getText());
					
					if (qty > 0) {
						quantity = qty; // Lưu số lượng
						dispose(); // Đóng cửa sổ
					} else {
						JOptionPane.showMessageDialog(Quantity.this, "Số lượng phải lớn hơn 0!", "Lỗi", JOptionPane.WARNING_MESSAGE);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(Quantity.this, "Vui lòng nhập số nguyên hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(209, 80, 113, 30);
		contentPane.add(btnNewButton);
	}


	public int getQuantity() {
		return quantity;
	}
}
