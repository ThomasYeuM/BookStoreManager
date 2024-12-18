package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import util.SendMail;
import dao.UserDao;
import model.User;

public class ForgotPasswordView extends JFrame {
	private JPanel contentPane;
	private JTextField emailField;
	private JTextField verificationCodeField;
	private JButton btnSendCode;
	private JButton btnVerifyCode;
	private boolean isCodeSent = false;
	private String sentOtp = "";

	// Các trường cho giao diện thay đổi mật khẩu
	private JPasswordField newPasswordField;
	private JPasswordField confirmPasswordField;
	private JButton btnSubmitNewPassword;

	public ForgotPasswordView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Các phần liên quan đến nhập email và mã OTP (giao diện cũ)
		JLabel lblEmail = new JLabel("Nhập Email để lấy lại mật khẩu");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(150, 40, 300, 35);
		contentPane.add(lblEmail);

		emailField = new JTextField();
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailField.setBounds(150, 90, 300, 35);
		contentPane.add(emailField);

		btnSendCode = new JButton("Gửi mã xác minh");
		btnSendCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSendCode.setBounds(200, 150, 200, 35);
		contentPane.add(btnSendCode);

		JLabel lblVerificationCode = new JLabel("Nhập mã xác minh");
		lblVerificationCode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVerificationCode.setBounds(150, 200, 300, 35);
		contentPane.add(lblVerificationCode);

		verificationCodeField = new JTextField();
		verificationCodeField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		verificationCodeField.setBounds(150, 240, 300, 35);
		contentPane.add(verificationCodeField);

		btnVerifyCode = new JButton("Kiểm tra mã");
		btnVerifyCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVerifyCode.setBounds(200, 280, 200, 35);
		btnVerifyCode.setEnabled(false); // Ban đầu chưa thể kiểm tra mã
		contentPane.add(btnVerifyCode);

		// Các phần liên quan đến nhập mật khẩu mới (giao diện thay đổi mật khẩu)
		JLabel lblNewPassword = new JLabel("Nhập mật khẩu mới");
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewPassword.setBounds(150, 40, 300, 35);
		lblNewPassword.setVisible(false);
		contentPane.add(lblNewPassword);

		newPasswordField = new JPasswordField();
		newPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		newPasswordField.setBounds(150, 90, 300, 35);
		newPasswordField.setVisible(false);
		contentPane.add(newPasswordField);

		JLabel lblConfirmPassword = new JLabel("Nhập lại mật khẩu mới");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblConfirmPassword.setBounds(150, 140, 300, 35);
		lblConfirmPassword.setVisible(false);
		contentPane.add(lblConfirmPassword);

		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		confirmPasswordField.setBounds(150, 190, 300, 35);
		confirmPasswordField.setVisible(false);
		contentPane.add(confirmPasswordField);

		btnSubmitNewPassword = new JButton("Cập nhật mật khẩu");
		btnSubmitNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSubmitNewPassword.setBounds(200, 240, 200, 35);
		btnSubmitNewPassword.setVisible(false);
		contentPane.add(btnSubmitNewPassword);

		// Đăng ký sự kiện cho nút gửi mã
		btnSendCode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = emailField.getText().trim();

				if (email.isEmpty()) {
					JOptionPane.showMessageDialog(ForgotPasswordView.this, "Vui lòng nhập email của bạn", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				SendMail sendMail = new SendMail();
				sentOtp = sendMail.sendNewPassword(email);

				if (sentOtp != null && !sentOtp.isEmpty()) {
					JOptionPane.showMessageDialog(ForgotPasswordView.this,
							"Mã xác minh đã được gửi đến email của bạn.");
					isCodeSent = true;
					btnVerifyCode.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(ForgotPasswordView.this,
							"Gửi mã xác minh không thành công. Vui lòng thử lại.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Đăng ký sự kiện cho nút kiểm tra mã OTP
		btnVerifyCode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String enteredCode = verificationCodeField.getText().trim();

				if (enteredCode.isEmpty()) {
					JOptionPane.showMessageDialog(ForgotPasswordView.this, "Vui lòng nhập mã xác minh", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (enteredCode.equals(sentOtp)) {
					JOptionPane.showMessageDialog(ForgotPasswordView.this,
							"OTP chính xác! Bạn có thể thay đổi mật khẩu.", "Success", JOptionPane.INFORMATION_MESSAGE);

					lblEmail.setVisible(false);
					emailField.setVisible(false);
					btnSendCode.setVisible(false);
					lblVerificationCode.setVisible(false);
					verificationCodeField.setVisible(false);
					btnVerifyCode.setVisible(false);

					lblNewPassword.setVisible(true);
					newPasswordField.setVisible(true);
					lblConfirmPassword.setVisible(true);
					confirmPasswordField.setVisible(true);
					btnSubmitNewPassword.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(ForgotPasswordView.this, "Mã xác minh không đúng.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnSubmitNewPassword.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String newPassword = new String(newPasswordField.getPassword()).trim();
		        String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

		        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
		            JOptionPane.showMessageDialog(ForgotPasswordView.this, "Vui lòng nhập đầy đủ thông tin mật khẩu.",
		                    "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        if (!newPassword.equals(confirmPassword)) {
		            JOptionPane.showMessageDialog(ForgotPasswordView.this,
		                    "Mật khẩu không khớp. Vui lòng kiểm tra lại.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        boolean isUpdated = updatePasswordInDB(newPassword);

		        if (isUpdated) {
		            JOptionPane.showMessageDialog(ForgotPasswordView.this, "Mật khẩu đã được cập nhật thành công.");

		            ForgotPasswordView.this.dispose(); 

		            new LoginView().setVisible(true);
		        } else {
		            JOptionPane.showMessageDialog(ForgotPasswordView.this,
		                    "Cập nhật mật khẩu thất bại. Vui lòng thử lại.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
	}
	private boolean updatePasswordInDB(String newPassword) {
	    String email = emailField.getText().trim();
	    if (email.isEmpty()) {
	        return false;
	    }
	    try {
	        UserDao userDao = new UserDao();
	        User userToUpdate = userDao.get(user -> user.getEmail().equals(email));

	        if (userToUpdate != null) {
	            userToUpdate.setPassword(newPassword);
	            return userDao.update(userToUpdate);
	        } else {
	            return false;
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return false;
	    }
	}
}
