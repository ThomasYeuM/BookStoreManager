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

    public AccountManagementView() throws ClassNotFoundException, IOException {
        userDao = new UserDao();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(
                        null,
                        "Do you really want to exit?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Quản lý tài khoản", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        contentPane.add(lblTitle, BorderLayout.NORTH);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Username", "Email", "Vai trò", "Trạng thái"}));
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));  // Layout để các nút được căn giữa
        contentPane.add(panelButtons, BorderLayout.SOUTH);

        JButton btnAddAccount = new JButton("Thêm tài khoản");
        btnAddAccount.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panelButtons.add(btnAddAccount);

        JButton btnEditAccount = new JButton("Sửa tài khoản");
        btnEditAccount.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panelButtons.add(btnEditAccount);

        JButton btnDeleteAccount = new JButton("Xóa tài khoản");
        btnDeleteAccount.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnDeleteAccount.setBackground(Color.red);
        btnDeleteAccount.setForeground(Color.white);
        panelButtons.add(btnDeleteAccount);

        JButton btnChangePassword = new JButton("Đổi mật khẩu tài khoản");
        btnChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panelButtons.add(btnChangePassword);

        btnAddAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddAccountView addAccountView = new AddAccountView(AccountManagementView.this);
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

        btnDeleteAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy thông tin người dùng từ bảng
                    int userId = (int) table.getValueAt(selectedRow, 0);
                    String username = (String) table.getValueAt(selectedRow, 1);
                    String email = (String) table.getValueAt(selectedRow, 2);
                    String role = (String) table.getValueAt(selectedRow, 3);
                    boolean isVerify = "Đã xác minh".equals(table.getValueAt(selectedRow, 4));
                    // Tạo đối tượng User để xóa
                    User userToDelete = new User(userId, username, email, "", role, isVerify);

                    // Kiểm tra vai trò trước khi xóa
                    if ("Admin".equalsIgnoreCase(role)) {
                        JOptionPane.showMessageDialog(AccountManagementView.this,
                                "Không thể xóa tài khoản Admin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // Hiển thị hộp thoại xác nhận
                    int confirmation = JOptionPane.showConfirmDialog(AccountManagementView.this,
                            "Bạn có chắc chắn muốn xóa tài khoản này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        try {
                            // Gọi DAO để xóa tài khoản
                            userDao.delete(userToDelete);

                            // Cập nhật lại dữ liệu trên bảng
                            getData();

                            JOptionPane.showMessageDialog(AccountManagementView.this,
                                    "Xóa tài khoản thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(AccountManagementView.this,
                                    "Lỗi khi xóa tài khoản.", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(AccountManagementView.this,
                            "Vui lòng chọn tài khoản để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int userId = (int) table.getValueAt(selectedRow, 0);
                    String username = (String) table.getValueAt(selectedRow, 1);

                    // Yêu cầu nhập mật khẩu mới
                    String newPassword = JOptionPane.showInputDialog(AccountManagementView.this,
                            "Nhập mật khẩu mới cho tài khoản " + username + ":");
                    if (newPassword != null && !newPassword.isEmpty()) {
                        try {
                            User userToChangePassword = userDao.get(u -> u.getId() == userId);
                            userToChangePassword.setPassword(newPassword); // Set new password for user
                            userDao.update(userToChangePassword); // Update the password in the database
                            JOptionPane.showMessageDialog(AccountManagementView.this,
                                    "Đổi mật khẩu cho tài khoản " + username + " thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(AccountManagementView.this,
                                    "Lỗi khi đổi mật khẩu.", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(AccountManagementView.this,
                                "Vui lòng nhập mật khẩu mới.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(AccountManagementView.this,
                            "Vui lòng chọn một tài khoản để đổi mật khẩu", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        getData();
    }

    public void getData() {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trong bảng
            var users = userDao.getAll();
            for (User user : users) {
                model.addRow(new Object[]{
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole(),
                        user.isVerify() ? "Đã xác minh" : "Chưa xác minh"
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
