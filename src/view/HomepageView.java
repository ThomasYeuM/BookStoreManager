package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class HomepageView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static String Username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomepageView frame = new HomepageView(Username);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomepageView(String Username) {
		this.Username = Username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);


		JButton qlySachBtn = new JButton("Quản lý sách");
		qlySachBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookManagementView bmv;
				bmv = new BookManagementView();
				bmv.setVisible(true);
			}
		});
		qlySachBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		qlySachBtn.setBounds(80, 25, 221, 50);
		contentPane.add(qlySachBtn);
		
		JButton qlHoaDonbtn = new JButton("Quản lý hóa đơn");
		qlHoaDonbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BillManagementView bmmv = new BillManagementView(Username);
				bmmv.setVisible(true);

			}
		});
		qlHoaDonbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		qlHoaDonbtn.setBounds(381, 25, 221, 50);
		contentPane.add(qlHoaDonbtn);
		
		JButton qlyTaiKhoanKHbtn = new JButton("Quản lý tài khoản");
		qlyTaiKhoanKHbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Khi nhấn vào nút, mở AccountManagementView
                AccountManagementView amv = new AccountManagementView();
                amv.setVisible(true);
                
            }
        });
		qlyTaiKhoanKHbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		qlyTaiKhoanKHbtn.setBounds(682, 25, 221, 50);
		contentPane.add(qlyTaiKhoanKHbtn);
		
		JButton chartsbtn = new JButton("Thống kê");
		chartsbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chartsbtn.setBounds(983, 25, 221, 50);
		contentPane.add(chartsbtn);
		
		JButton qlyCategoryBtn = new JButton("Quản lý thể loại");
		qlyCategoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoryManagementView categoryManagement = new CategoryManagementView();
				categoryManagement.setVisible(true);
			}
		});

		qlyCategoryBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		qlyCategoryBtn.setBounds(80, 120, 221, 50);
		contentPane.add(qlyCategoryBtn);
		
		JButton doiMatKhaubtn = new JButton("Đổi mật khẩu");
		doiMatKhaubtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Khi nhấn vào nút, mở AccountManagementView
                AccountManagementView amv = new AccountManagementView();
                amv.setVisible(true);
                
            }
        });
		doiMatKhaubtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		doiMatKhaubtn.setBounds(400, 120, 221, 50);
		contentPane.add(doiMatKhaubtn);
		
		

	}

}
