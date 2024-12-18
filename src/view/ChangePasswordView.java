package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import dao.UserDao;
import model.User;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePasswordView extends JFrame {
    private JPanel contentPane;
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    private String username;
    private HomepageView homepageView;

    // Constructor nhận vào username và HomepageView
    public ChangePasswordView(String username, HomepageView homepageView) {
        this.username = username;
        this.homepageView = homepageView;

        setTitle("Đổi mật khẩu");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Label Mật khẩu cũ
        JLabel lblOldPassword = new JLabel("Mật khẩu cũ:");
        lblOldPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblOldPassword.setBounds(30, 30, 120, 25);
        contentPane.add(lblOldPassword);

        // Text field nhập mật khẩu cũ
        oldPasswordField = new JPasswordField();
        oldPasswordField.setBounds(160, 30, 200, 25);
        contentPane.add(oldPasswordField);

        // Label Mật khẩu mới
        JLabel lblNewPassword = new JLabel("Mật khẩu mới:");
        lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewPassword.setBounds(30, 80, 120, 25);
        contentPane.add(lblNewPassword);

        // Text field nhập mật khẩu mới
        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(160, 80, 200, 25);
        contentPane.add(newPasswordField);

        // Label Nhập lại mật khẩu
        JLabel lblConfirmPassword = new JLabel("Nhập lại mật khẩu:");
        lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblConfirmPassword.setBounds(30, 130, 120, 25);
        contentPane.add(lblConfirmPassword);

        // Text field nhập lại mật khẩu
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(160, 130, 200, 25);
        contentPane.add(confirmPasswordField);

        // Nút Đổi mật khẩu
        JButton btnChangePassword = new JButton("Đổi mật khẩu");
        btnChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnChangePassword.setBounds(140, 180, 120, 30);
        btnChangePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String oldPassword = new String(oldPasswordField.getPassword());
                String newPassword = new String(newPasswordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                // Kiểm tra thông tin nhập
                if (!oldPassword.isEmpty() && !newPassword.isEmpty() && !confirmPassword.isEmpty()) {
                    if (!newPassword.equals(confirmPassword)) {
                        JOptionPane.showMessageDialog(ChangePasswordView.this, 
                            "Mật khẩu mới và nhập lại mật khẩu không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        UserDao userDao = new UserDao();
                        User currentUser = userDao.get(user -> user.getUsername().equals(username));

                        if (currentUser != null && currentUser.getPassword().equals(oldPassword)) {
                            currentUser.setPassword(newPassword);
                            boolean isUpdated = userDao.update(currentUser);

                            if (isUpdated) {
                                JOptionPane.showMessageDialog(ChangePasswordView.this, 
                                    "Đổi mật khẩu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                
                                // Sau khi đổi mật khẩu thành công, quay lại HomepageView
                                dispose();  // Đóng cửa sổ đổi mật khẩu
                                homepageView.setVisible(true);  // Mở lại trang chủ
                            } else {
                                JOptionPane.showMessageDialog(ChangePasswordView.this, 
                                    "Đổi mật khẩu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(ChangePasswordView.this, 
                                "Mật khẩu cũ không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(ChangePasswordView.this, 
                            "Lỗi khi đổi mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(ChangePasswordView.this, 
                        "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        contentPane.add(btnChangePassword);
    }
}
