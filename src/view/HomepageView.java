package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import dao.BillDAO;
import model.Bill;


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
                AccountManagementView amv = null;

					try {
						amv = new AccountManagementView();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

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
		chartsbtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            BillDAO billDao = new BillDAO();
		            List<Bill> bills = billDao.getAll(); // Lấy danh sách hóa đơn từ DAO
		            StatisticsView statisticsView = new StatisticsView(bills);
		            statisticsView.setVisible(true);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Không thể tải dữ liệu thống kê.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
		    }
		});


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
                ChangePasswordView changePasswordView = new ChangePasswordView(Username, HomepageView.this);
                changePasswordView.setVisible(true);
                HomepageView.this.setVisible(false); 
            }
        });
        doiMatKhaubtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        doiMatKhaubtn.setBounds(400, 120, 221, 50);
        contentPane.add(doiMatKhaubtn);
        JButton dangXuatBtn = new JButton("Đăng xuất");
		dangXuatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Quay lại trang đăng nhập
				LoginView loginView = new LoginView();
				loginView.setVisible(true);
				HomepageView.this.setVisible(false); // Ẩn trang chủ
			}
		});
		dangXuatBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dangXuatBtn.setBounds(700, 120, 221, 50);
		contentPane.add(dangXuatBtn);
		
		JButton taoHoaDonBtn = new JButton("Tạo hóa đơn");
		taoHoaDonBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		taoHoaDonBtn.setBounds(983, 120, 221, 50); // Đặt vị trí và kích thước cho nút
		taoHoaDonBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Mở cửa sổ BillView cho người dùng
		        BillView billView = new BillView(Username); // Truyền username vào constructor
		        billView.setVisible(true); // Hiển thị cửa sổ tạo hóa đơn
		    }
		});
		contentPane.add(taoHoaDonBtn);
	}

}
