package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UsernameField;
	private JTextField PasswordTextField;

	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Logo
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

		PasswordTextField = new JTextField();
		PasswordTextField.setColumns(10);
		PasswordTextField.setBounds(519, 210, 346, 42);
		PasswordTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		PasswordTextField.setBorder(BorderFactory.createCompoundBorder(PasswordTextField.getBorder(),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		contentPane.add(PasswordTextField);

		JButton LoginButton = new JButton("Đăng nhập");
		LoginButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( !FormUtils.ValidateForm(contentPane) ) {
					JOptionPane.showMessageDialog(LoginView.this, "Vui lòng nhập đầy đủ thông tin", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
