package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import dao.UserDao;
import model.User;
import java.io.IOException;
import java.util.List;

public class AddAccountView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private JCheckBox chkIsVerified;
	private UserDao userDao;
	private AccountManagementView parentView; 

	public AddAccountView(AccountManagementView parentView) {
		this.parentView = parentView; 
		userDao = new UserDao();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(5, 2));

		JLabel lblUsername = new JLabel("Username:");
		contentPane.add(lblUsername);

		txtUsername = new JTextField();
		contentPane.add(txtUsername);

		JLabel lblEmail = new JLabel("Email:");
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		contentPane.add(txtEmail);

		JLabel lblPassword = new JLabel("Password:");
		contentPane.add(lblPassword);

		txtPassword = new JPasswordField();
		contentPane.add(txtPassword);

		JLabel lblVerify = new JLabel("Xác minh tài khoản:");
		contentPane.add(lblVerify);

		chkIsVerified = new JCheckBox("Đã xác minh");
		contentPane.add(chkIsVerified);

		JButton btnAdd = new JButton("Thêm tài khoản");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(new JLabel());
		contentPane.add(btnAdd);

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String email = txtEmail.getText();
				String password = new String(txtPassword.getPassword());

				if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
                        List<User> users = userDao.getAll();
                        boolean isUsernameTaken = users.stream().anyMatch(user -> user.getUsername().equals(username)); // Kiểm tra username có tồn tại

                        if (isUsernameTaken) {
                            JOptionPane.showMessageDialog(null, "Username đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        } else {
                            boolean isVerify = chkIsVerified.isSelected();
                            
                            int maxId = users.stream().mapToInt(User::getId).max().orElse(0);
                            int newId = maxId + 1;

                            User newUser = new User(newId, username, email, password, isVerify); 

                            boolean result = userDao.add(newUser);
                            if (result) {
                                JOptionPane.showMessageDialog(null, "Thêm tài khoản thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                parentView.getData(); 
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                            }
                        }
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Lỗi khi thêm tài khoản.", "Thông báo",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
}
