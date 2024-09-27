package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import util.FormUtils;

import javax.swing.JPasswordField;
import util.ImageUtils;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UsernameField;
	private JPasswordField passwordField;
	private JButton showPasswordButton;
	private ImageUtils imageUtils = new ImageUtils();

	public LoginView() {
		String FILE_PATH = "src/db/users.txt";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			Image image = ImageIO.read(new File("src/resources/logo.png"));
			JLabel label = new JLabel(new ImageIcon(image));
			label.setBounds(60, 79, 398, 268);
			contentPane.add(label);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel lblNewLabel = new JLabel("Tên đăng nhập");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(519, 77, 237, 35);
		contentPane.add(lblNewLabel);

		UsernameField = new JTextField();
		UsernameField.setHorizontalAlignment(SwingConstants.LEFT);
		UsernameField.setBounds(519, 113, 346, 42);
		UsernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		UsernameField.setBorder(BorderFactory.createCompoundBorder(UsernameField.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		contentPane.add(UsernameField);
		UsernameField.setColumns(10);

		JLabel lblMtKhu = new JLabel("Mật khẩu");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMtKhu.setBounds(519, 175, 237, 35);
		contentPane.add(lblMtKhu);

		JButton LoginButton = new JButton("Đăng nhập");
		LoginButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!FormUtils.ValidateForm(contentPane)) {
					JOptionPane.showMessageDialog(LoginView.this, "Vui lòng nhập đầy đủ thông tin", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String username = UsernameField.getText();
				char[] password = passwordField.getPassword();

				String password0 = new String(password);
				try {
					UserDao ud = new UserDao();
					User user = ud.get(u -> u.getUsername().equals(username));

					if (user == null || !user.getPassword().equals(password0)) {
						JOptionPane.showMessageDialog(LoginView.this, "Tên đăng nhập hoặc mật khẩu không chính xác.",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (!user.isVerify()) {
						JOptionPane.showMessageDialog(LoginView.this,
								"Tài khoản của bạn không có quyền truy cập. Vui lòng liên hệ Admin.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showMessageDialog(LoginView.this, "Đăng nhập thành công.");
					dispose();
					HomepageView homepage = new HomepageView();
					homepage.setVisible(true);

					return;

				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		LoginButton.setBounds(519, 269, 205, 38);
		contentPane.add(LoginButton);

		JLabel lblNewLabel_1 = new JLabel("Chưa có tài khoản?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(529, 323, 168, 20);
		contentPane.add(lblNewLabel_1);

		JButton RegisterButton = new JButton("Đăng kí");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SignupView sg = new SignupView();
				sg.setVisible(true);
				dispose();

			}
		});
		RegisterButton.setBounds(673, 324, 89, 23);
		contentPane.add(RegisterButton);

		JButton ForgotPasswordButton = new JButton("Lấy lại mật khẩu");
		ForgotPasswordButton.setBackground(new Color(255, 255, 255));
		ForgotPasswordButton.setBounds(734, 279, 131, 23);
		ForgotPasswordButton.setBorderPainted(false);
		contentPane.add(ForgotPasswordButton);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(519, 220, 346, 39);
		passwordField.setBorder(BorderFactory.createCompoundBorder(UsernameField.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		contentPane.add(passwordField);

		// Create the "Show Password" button

		try {
			JLabel CloseEyeIcon = imageUtils.getImage("src/resources/closed-eyes.png");
			JLabel OpenEyeIcon = imageUtils.getImage("src/resources/eye-close-up.png");
			showPasswordButton = new JButton("Show");
			showPasswordButton.setBounds(875, 220, 130, 39);
			showPasswordButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (passwordField.getEchoChar() == '\u0000') {
						// Password is visible
						passwordField.setEchoChar('*'); // Hide password
						showPasswordButton.setText("Hide");
					} else { // Password is hidden
						passwordField.setEchoChar('\u0000'); // Show password
						showPasswordButton.setText("Show");
					}
				}
			});
			contentPane.add(showPasswordButton);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
