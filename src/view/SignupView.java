package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import util.FormUtils;

public class SignupView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UsernameField;
	private JTextField PasswordTextField;
	private JTextField ConfirmPasswordTextField;
	private JTextField EmailTextField;

	public SignupView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên đăng nhập");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(518, 11, 237, 35);
		contentPane.add(lblNewLabel);
		
		UsernameField = new JTextField();
		UsernameField.setHorizontalAlignment(SwingConstants.LEFT);
		UsernameField.setBounds(518, 47, 346, 42);
		UsernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		UsernameField.setBorder(BorderFactory.createCompoundBorder(UsernameField.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		contentPane.add(UsernameField);
		UsernameField.setColumns(10);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMtKhu.setBounds(518, 188, 237, 35);
		contentPane.add(lblMtKhu);
		
		PasswordTextField = new JTextField();
		PasswordTextField.setColumns(10);
		PasswordTextField.setBounds(518, 223, 346, 42);
		PasswordTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		PasswordTextField.setBorder(BorderFactory.createCompoundBorder(PasswordTextField.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		contentPane.add(PasswordTextField);
		
		JButton SignupButton = new JButton("Đăng kí");
		SignupButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		SignupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!FormUtils.ValidateForm(contentPane)) {
					JOptionPane.showMessageDialog(SignupView.this, "Vui lòng nhập đầy đủ thông tin", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
					
				}
				
				String username = UsernameField.getText();
				String password = PasswordTextField.getText();
				String confirmPassword = ConfirmPasswordTextField.getText();
				String email = EmailTextField.getText();
				
				
				if (!password.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(SignupView.this, "Vui lòng kiểm tra lại mật khẩu", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		SignupButton.setBounds(518, 374, 205, 38);
		contentPane.add(SignupButton);
		
		JLabel lblNewLabel_1 = new JLabel("Đã có tài khoản?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(518, 423, 168, 20);
		contentPane.add(lblNewLabel_1);
		
		JButton RegisterButton = new JButton("Đăng nhập");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LoginView lg = new LoginView();
				lg.setVisible(true);
				dispose();
			}
		});
		RegisterButton.setBounds(656, 424, 104, 23);
		contentPane.add(RegisterButton);
		
		JLabel lblMtKhu_1 = new JLabel("Nhập lại mật khẩu");
		lblMtKhu_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMtKhu_1.setBounds(518, 276, 237, 35);
		contentPane.add(lblMtKhu_1);
		
		ConfirmPasswordTextField = new JTextField();
		ConfirmPasswordTextField.setColumns(10);
		ConfirmPasswordTextField.setBounds(518, 311, 346, 42);
		ConfirmPasswordTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ConfirmPasswordTextField.setBorder(BorderFactory.createCompoundBorder(ConfirmPasswordTextField.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		contentPane.add(ConfirmPasswordTextField);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(518, 100, 237, 35);
		contentPane.add(lblEmail);
		
		EmailTextField = new JTextField();
		EmailTextField.setColumns(10);
		EmailTextField.setBounds(518, 135, 346, 42);
		EmailTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		EmailTextField.setBorder(BorderFactory.createCompoundBorder(EmailTextField.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		contentPane.add(EmailTextField);
	}

}
