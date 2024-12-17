package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import dao.UserDao;
import model.User;

public class EditAccountView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtUsername;
	private JTextField txtEmail;
	private JCheckBox chckbxVerify;
	private JButton btnSave;
	private JButton btnCancel;
	private UserDao userDao;
	private User userToEdit;
	private AccountManagementView parentView;

	public EditAccountView(User user, AccountManagementView parentView) {
		userDao = new UserDao();
		this.userToEdit = user;
		this.parentView = parentView;


		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	addWindowListener(new WindowAdapter() {
    	    @Override
    	    public void windowClosing(WindowEvent e) {
    	        // Show confirmation dialog
    	        if (JOptionPane.showConfirmDialog(
    	            null, 
    	            "Do you really want to exit?", 
    	            "Confirm", 
    	            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    	            
    	            // Close current window
    	            dispose();
    	            
    	            // Open HomepageView
    
    	        }
    	    }
    	});

		setBounds(100, 100, 400, 300);
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(4, 2, 10, 10));
		setContentPane(contentPane);

		JLabel lblUsername = new JLabel("Username:");
		txtUsername = new JTextField(user.getUsername());
		JLabel lblEmail = new JLabel("Email:");
		txtEmail = new JTextField(user.getEmail());
		JLabel lblVerify = new JLabel("Trạng thái xác minh:");
		chckbxVerify = new JCheckBox();
		chckbxVerify.setSelected(user.isVerify());

		btnSave = new JButton("Lưu");
		btnCancel = new JButton("Hủy");

		contentPane.add(lblUsername);
		contentPane.add(txtUsername);
		contentPane.add(lblEmail);
		contentPane.add(txtEmail);
		contentPane.add(lblVerify);
		contentPane.add(chckbxVerify);
		contentPane.add(btnSave);
		contentPane.add(btnCancel);

		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userToEdit.setUsername(txtUsername.getText());
				userToEdit.setEmail(txtEmail.getText());
				userToEdit.setVerify(chckbxVerify.isSelected());

				try {
					if (userDao.update(userToEdit)) {
						JOptionPane.showMessageDialog(EditAccountView.this, "Cập nhật tài khoản thành công!",
								"Thông báo", JOptionPane.INFORMATION_MESSAGE);
						parentView.getData();
						dispose();
					} else {
						JOptionPane.showMessageDialog(EditAccountView.this, "Cập nhật tài khoản thất bại!", "Thông báo",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(EditAccountView.this, "Lỗi khi cập nhật tài khoản.", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}