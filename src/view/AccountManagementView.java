package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import dao.UserDao;
import model.User;

public class AccountManagementView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private UserDao userDao;

	public AccountManagementView() {
		userDao = new UserDao(); //
		
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
//    	            new HomepageView().setVisible(true);
    	        }
    	    }
    	});

	
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		JLabel lblTitle = new JLabel("Quản lý tài khoản", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblTitle, BorderLayout.NORTH);

		table = new JTable();
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Username", "Email", "Trạng thái" }));
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JPanel panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.SOUTH);

		JButton btnAddAccount = new JButton("Thêm tài khoản");
		btnAddAccount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelButtons.add(btnAddAccount);

		JButton btnEditAccount = new JButton("Sửa tài khoản");
		btnEditAccount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelButtons.add(btnEditAccount);

		JButton btnDeleteAccount = new JButton("Xóa tài khoản");
		btnDeleteAccount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelButtons.add(btnDeleteAccount);

		btnAddAccount.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        AddAccountView addAccountView = new AddAccountView(AccountManagementView.this); // Truyền đối tượng AccountManagementView
		        addAccountView.setVisible(true);
		        dispose();
		    }
		});

		btnEditAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					int userId = (int) table.getValueAt(selectedRow, 0);
					User userToEdit = null;
					try {
						userToEdit = userDao.get(u -> u.getId() == userId);
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					EditAccountView editAccountView = new EditAccountView(userToEdit, AccountManagementView.this);
					editAccountView.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(AccountManagementView.this,
							"Vui lòng chọn một tài khoản để chỉnh sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		//
		btnDeleteAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					int userId = (int) table.getValueAt(selectedRow, 0);
					String username = (String) table.getValueAt(selectedRow, 1);
					String email = (String) table.getValueAt(selectedRow, 2);
					boolean isVerify = "Đã xác minh".equals(table.getValueAt(selectedRow, 3));

					User userToDelete = new User(userId, username, email, "", isVerify);
					int confirmation = JOptionPane.showConfirmDialog(AccountManagementView.this,
							"Bạn có chắc chắn muốn xóa tài khoản này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
					if (confirmation == JOptionPane.YES_OPTION) {
						try {
							userDao.delete(userToDelete); 
							getData(); 
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(AccountManagementView.this, "Lỗi khi xóa tài khoản.",
									"Thông báo", JOptionPane.ERROR_MESSAGE);
						}
					}
				} else {
					JOptionPane.showMessageDialog(AccountManagementView.this, "Vui lòng chọn tài khoản để xóa.",
							"Thông báo", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		//
		getData();
	}

	//
	public void getData() {
		try {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			var users = userDao.getAll();
			for (User user : users) {
				model.addRow(new Object[] { user.getId(), user.getUsername(), user.getEmail(),
						user.isVerify() ? "Đã xác minh" : "Chưa xác minh" });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
