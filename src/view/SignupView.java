package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import dao.UserDao;
import model.User;
import util.FormUtils;
import util.SendMail;

public class SignupView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField UsernameField;
    private JTextField PasswordTextField;
    private JTextField ConfirmPasswordTextField;
    private JTextField EmailTextField;
    private JTextField verificationCodeField;
    private JButton sendVerificationCodeButton;

    public SignupView() {
        SendMail sendMail = new SendMail();
        UserDao userDao = new UserDao();
        UserController userController = new UserController(userDao);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600); // Điều chỉnh kích thước cửa sổ
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Logo
        try {
            Image image = ImageIO.read(new File("src/resources/logo.png"));
            JLabel label = new JLabel(new ImageIcon(image));
            label.setBounds(60, 79, 398, 268);
            contentPane.add(label);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Tên đăng nhập
        JLabel lblNewLabel = new JLabel("Tên đăng nhập");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(518, 20, 237, 35);
        contentPane.add(lblNewLabel);

        UsernameField = new JTextField();
        UsernameField.setHorizontalAlignment(SwingConstants.LEFT);
        UsernameField.setBounds(518, 55, 346, 42);
        UsernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        UsernameField.setBorder(BorderFactory.createCompoundBorder(UsernameField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(UsernameField);
        UsernameField.setColumns(10);

        // Mật khẩu
        JLabel lblMtKhu = new JLabel("Mật khẩu");
        lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblMtKhu.setBounds(518, 100, 237, 35);
        contentPane.add(lblMtKhu);

        PasswordTextField = new JTextField();
        PasswordTextField.setColumns(10);
        PasswordTextField.setBounds(518, 135, 346, 42);
        PasswordTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        PasswordTextField.setBorder(BorderFactory.createCompoundBorder(PasswordTextField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(PasswordTextField);

        // Nhập lại mật khẩu
        JLabel lblMtKhu_1 = new JLabel("Nhập lại mật khẩu");
        lblMtKhu_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblMtKhu_1.setBounds(518, 180, 237, 35);
        contentPane.add(lblMtKhu_1);

        ConfirmPasswordTextField = new JTextField();
        ConfirmPasswordTextField.setColumns(10);
        ConfirmPasswordTextField.setBounds(518, 215, 346, 42);
        ConfirmPasswordTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ConfirmPasswordTextField.setBorder(BorderFactory.createCompoundBorder(ConfirmPasswordTextField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(ConfirmPasswordTextField);

        // Email
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEmail.setBounds(518, 260, 237, 35);
        contentPane.add(lblEmail);

        EmailTextField = new JTextField();
        EmailTextField.setColumns(10);
        EmailTextField.setBounds(518, 295, 346, 42);
        EmailTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        EmailTextField.setBorder(BorderFactory.createCompoundBorder(EmailTextField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(EmailTextField);

        // Mã xác minh
        JLabel lblVerificationCode = new JLabel("Mã xác minh");
        lblVerificationCode.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblVerificationCode.setBounds(518, 350, 237, 35);
        contentPane.add(lblVerificationCode);

        verificationCodeField = new JTextField();
        verificationCodeField.setColumns(10);
        verificationCodeField.setBounds(518, 385, 250, 42);
        verificationCodeField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        verificationCodeField.setBorder(BorderFactory.createCompoundBorder(verificationCodeField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        contentPane.add(verificationCodeField);

        sendVerificationCodeButton = new JButton("Gửi mã");
        sendVerificationCodeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        sendVerificationCodeButton.setBounds(778, 385, 100, 42);
        sendVerificationCodeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = EmailTextField.getText();
                boolean isSent = sendMail.sendOtp(email);

                if (isSent) {
                    JOptionPane.showMessageDialog(SignupView.this, "Mã xác minh đã được gửi đến email của bạn!");
                } else {
                    JOptionPane.showMessageDialog(SignupView.this, "Có lỗi xảy ra khi gửi mã xác minh!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(sendVerificationCodeButton);

        // Đăng ký
        JButton SignupButton = new JButton("Đăng kí");
        SignupButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        SignupButton.setBounds(518, 450, 205, 38);
        SignupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Kiểm tra thông tin
                if (!FormUtils.ValidateForm(contentPane)) {
                    JOptionPane.showMessageDialog(SignupView.this, "Vui lòng nhập đầy đủ thông tin", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String username = UsernameField.getText();
                String password = PasswordTextField.getText();
                String confirmPassword = ConfirmPasswordTextField.getText();
                String email = EmailTextField.getText();
                String enteredOtp = verificationCodeField.getText().trim();

                // Kiểm tra OTP
                if (enteredOtp.isEmpty()) {
                    JOptionPane.showMessageDialog(SignupView.this, "Bạn phải nhập mã xác minh!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!enteredOtp.equals(sendMail.getOtp())) {
                    JOptionPane.showMessageDialog(SignupView.this, "Mã xác minh không đúng. Vui lòng thử lại.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra mật khẩu
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(SignupView.this, "Mật khẩu và mật khẩu xác nhận không trùng khớp", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int id = userController.getAllUsers().size() + 1;
                    User newUser = new User(id, username, email, password, false);

                    if (userController.addUser(newUser)) {
                        JOptionPane.showMessageDialog(SignupView.this, "Đăng ký thành công!");

                        LoginView loginView = new LoginView();
                        loginView.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(SignupView.this, "Tên đăng nhập hoặc email đã tồn tại. Vui lòng thử lại!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SignupView.this, "Đã xảy ra lỗi hệ thống. Vui lòng thử lại sau.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SignupView.this, "Lỗi hệ thống: Không tìm thấy class cần thiết.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(SignupButton);

        // Đã có tài khoản?
        JLabel lblNewLabel_1 = new JLabel("Đã có tài khoản?");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(518, 500, 168, 20);
        contentPane.add(lblNewLabel_1);

        JButton RegisterButton = new JButton("Đăng nhập");
        RegisterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginView lg = new LoginView();
                lg.setVisible(true);
                dispose();
            }
        });
        RegisterButton.setBounds(656, 500, 104, 23);
        contentPane.add(RegisterButton);
    }
}
