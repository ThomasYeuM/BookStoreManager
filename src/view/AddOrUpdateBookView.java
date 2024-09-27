package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddOrUpdateBookView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JLabel lblTnSch_1;
	private JTextField textField;
	private JLabel lblSLng;
	private JTextField textField_2;
	private JLabel lblGi;
	private JTextField textField_3;
	private JLabel lblTcGi;
	private JTextField textField_4;
	private JLabel lblTnSch_5;
	private JTextField textField_5;
	private JButton btnNewButton;

	/**
	 * Create the frame.
	 */
	public AddOrUpdateBookView(String action) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTnSch = new JLabel("Mã sách");
		lblTnSch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTnSch.setBounds(42, 96, 133, 30);
		contentPane.add(lblTnSch);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(42, 129, 307, 30);
		contentPane.add(textField_1);
		
		lblTnSch_1 = new JLabel("Tên sách");
		lblTnSch_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTnSch_1.setBounds(42, 175, 133, 30);
		contentPane.add(lblTnSch_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(42, 208, 307, 30);
		contentPane.add(textField);
		
		lblSLng = new JLabel("Số lượng");
		lblSLng.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSLng.setBounds(42, 249, 133, 30);
		contentPane.add(lblSLng);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(42, 282, 307, 30);
		contentPane.add(textField_2);
		
		lblGi = new JLabel("Giá");
		lblGi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGi.setBounds(42, 323, 133, 30);
		contentPane.add(lblGi);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(42, 356, 307, 30);
		contentPane.add(textField_3);
		
		lblTcGi = new JLabel("Tác giả");
		lblTcGi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTcGi.setBounds(42, 397, 133, 30);
		contentPane.add(lblTcGi);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(42, 430, 307, 30);
		contentPane.add(textField_4);
		
		lblTnSch_5 = new JLabel("Thông tin");
		lblTnSch_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTnSch_5.setBounds(42, 471, 133, 30);
		contentPane.add(lblTnSch_5);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(42, 504, 307, 30);
		contentPane.add(textField_5);
		
		btnNewButton = new JButton("Thêm");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(124, 576, 142, 45);
		contentPane.add(btnNewButton);
	}
}
