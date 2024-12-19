package view;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.BillController;
import controller.BookController;
import dao.BillDAO;
import dao.BookDao;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.ActionEvent;

import model.Bill;
import model.User;

public class HomepageView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static String Username;
    private static String Role;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HomepageView frame = new HomepageView(Username,Role);
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
    public HomepageView(String Username, String role) {
    	Image icon = Toolkit.getDefaultToolkit().getImage(SignupView.class.getResource("/resources/logo.png"));
		this.setIconImage(icon);
		setResizable(false);
        this.Username = Username;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1280, 720);
        setTitle("HaUI BookStore Management");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/resources/haui_logo.png"));
		Image logoImg = logoIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		logoIcon = new ImageIcon(logoImg);
        
		String helloText = "Xin chào, " + Username + "!";
		JLabel helloLabel = new JLabel(helloText);
		helloLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		helloLabel.setBounds(10, 11, 328, 28);
		contentPane.add(helloLabel);

		JLabel titleLabel = new JLabel("Hệ thống quản lý HAUI BookStore", SwingConstants.CENTER);
		titleLabel.setForeground(Color.ORANGE);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		titleLabel.setBounds(0, 41, 1264, 101);
		contentPane.add(titleLabel);

        JButton qlySachBtn = new JButton("Quản lý sách");
        qlySachBtn.setIcon(new ImageIcon(getClass().getResource("/resources/book.png")));
        qlySachBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BookManagementView bmv = new BookManagementView();
                bmv.setVisible(true);
            }
        });
        qlySachBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        qlySachBtn.setBounds(107, 250, 292, 107);
        contentPane.add(qlySachBtn);

        JButton qlHoaDonbtn = new JButton("Quản lý hóa đơn");
        qlHoaDonbtn.setIcon(new ImageIcon(getClass().getResource("/resources/order.png")));
        qlHoaDonbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BillManagementView bmmv = new BillManagementView(Username);
                bmmv.setVisible(true);
            }
        });
        qlHoaDonbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        qlHoaDonbtn.setBounds(489, 250, 292, 107);
        contentPane.add(qlHoaDonbtn);

        JButton qlyTaiKhoanKHbtn = new JButton("Quản lý tài khoản");
        qlyTaiKhoanKHbtn.setIcon(new ImageIcon(getClass().getResource("/resources/verified-account.png")));
        qlyTaiKhoanKHbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AccountManagementView amv = null;
                try {
                    amv = new AccountManagementView();
                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
                amv.setVisible(true);
            }
        });
        qlyTaiKhoanKHbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        qlyTaiKhoanKHbtn.setBounds(852, 250, 292, 107);
        contentPane.add(qlyTaiKhoanKHbtn);

        JButton chartsbtn = new JButton("Thống kê");
        chartsbtn.setIcon(new ImageIcon(getClass().getResource("/resources/description.png")));
        chartsbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        chartsbtn.setBounds(489, 536, 292, 107);
        contentPane.add(chartsbtn);

        JButton qlyCategoryBtn = new JButton("Quản lý thể loại");
        qlyCategoryBtn.setIcon(new ImageIcon(getClass().getResource("/resources/product-management2.png")));
        qlyCategoryBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CategoryManagementView categoryManagement = new CategoryManagementView();
                categoryManagement.setVisible(true);
            }
        });
        qlyCategoryBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        qlyCategoryBtn.setBounds(107, 393, 292, 107);
        contentPane.add(qlyCategoryBtn);

        JButton doiMatKhaubtn = new JButton("Đổi mật khẩu");
        doiMatKhaubtn.setIcon(new ImageIcon(getClass().getResource("/resources/secured-laptop.png")));
        doiMatKhaubtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChangePasswordView changePasswordView = new ChangePasswordView(Username, HomepageView.this);
                changePasswordView.setVisible(true);
                HomepageView.this.setVisible(false);
            }
        });
        doiMatKhaubtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        doiMatKhaubtn.setBounds(852, 393, 292, 103);
        contentPane.add(doiMatKhaubtn);

        JButton dangXuatBtn = new JButton("Đăng xuất");
        dangXuatBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginView loginView = new LoginView();
                loginView.setVisible(true);
                HomepageView.this.setVisible(false);
            }
        });
        dangXuatBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        dangXuatBtn.setBounds(1128, 11, 126, 32);
        contentPane.add(dangXuatBtn);
        
        ImageIcon addBillIcon = new ImageIcon(getClass().getResource("/resources/create-bill-icon.png"));
		Image addBillImg = addBillIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		addBillIcon = new ImageIcon(addBillImg);

        JButton taoHoaDonBtn = new JButton("Tạo hóa đơn");
        taoHoaDonBtn.setIcon((Icon) addBillIcon);
        taoHoaDonBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        taoHoaDonBtn.setBounds(489, 393, 292, 107);
        taoHoaDonBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BillView billView = new BillView(Username);
                billView.setVisible(true);
            }
        });
        contentPane.add(taoHoaDonBtn);
        JPanel productPanel = new JPanel();
		productPanel.setBounds(200, 125, 250, 101);
		contentPane.add(productPanel);
		productPanel.setLayout(null);

		ImageIcon productIcon = new ImageIcon(getClass().getResource("/resources/product-icon.png"));
		Image productImg = productIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		productIcon = new ImageIcon(productImg);

		JLabel productImage = new JLabel(productIcon);
		productImage.setForeground(new Color(255, 200, 0));
		productImage.setBounds(0, 0, 101, 101);
		productImage.setOpaque(true);
		productPanel.add(productImage);

		JLabel productTitle = new JLabel("Số sản phẩm", SwingConstants.CENTER);
		productTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		productTitle.setBounds(111, 11, 139, 21);
		productPanel.add(productTitle);  // Thêm vào productPanel

		BookDao bookDao = new BookDao();
		BookController bookController = new BookController(bookDao);
		int booknums = 0;
		try {
		    booknums = bookController.getAllBooks().size();
		    JLabel books = new JLabel(String.valueOf(booknums), SwingConstants.CENTER);
		    books.setFont(new Font("Tahoma", Font.PLAIN, 40));
		    books.setBounds(111, 43, 139, 47);
		    productPanel.add(books);
		} catch (Exception e1) {
		    e1.printStackTrace();
		}
		
		ImageIcon billIcon = new ImageIcon(getClass().getResource("/resources/bill-icon.png"));
		Image billImg = billIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		billIcon = new ImageIcon(billImg);
		productPanel.add(productImage);
		JPanel billPanel = new JPanel();
		billPanel.setLayout(null);
		billPanel.setBounds(505, 125, 250, 101);
		contentPane.add(billPanel);

		JLabel billImage = new JLabel((Icon) billIcon);
		billImage.setOpaque(true);
		billImage.setForeground(Color.ORANGE);
		billImage.setBounds(0, 0, 101, 101);
		billPanel.add(billImage);

		JLabel billTitle = new JLabel("Hoá đơn đã tạo", SwingConstants.CENTER);
		billTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		billTitle.setBounds(111, 11, 139, 21);
		billPanel.add(billTitle);

		BillDAO billDAO = new BillDAO();
		BillController billController = new BillController(billDAO, null);
		try {
			int billNums = billController.getAllBills().size();
			JLabel billNum = new JLabel(String.valueOf(billNums), SwingConstants.CENTER);
			billNum.setFont(new Font("Tahoma", Font.PLAIN, 40));
			billNum.setBounds(111, 43, 139, 47);
			billPanel.add(billNum);
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
		JPanel revenuePanel = new JPanel();
		revenuePanel.setLayout(null);
		revenuePanel.setBounds(810, 125, 300, 101);
		contentPane.add(revenuePanel);

		ImageIcon revenueIcon = new ImageIcon(getClass().getResource("/resources/revenue-icon.png"));
		Image revenueImg = revenueIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		revenueIcon = new ImageIcon(revenueImg);
		productPanel.add(productImage);

		JLabel revenueImage = new JLabel((Icon) revenueIcon);
		revenueImage.setOpaque(true);
		revenueImage.setForeground(Color.ORANGE);
		revenueImage.setBounds(0, 0, 101, 101);
		revenuePanel.add(revenueImage);

		JLabel revenueTitle = new JLabel("Doanh thu tháng này", SwingConstants.CENTER);
        revenueTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
        revenueTitle.setBounds(111, 11, 189, 21);
        revenuePanel.add(revenueTitle);

        try {
            double revenue = 0;
            List<Bill> bills = billController.getAllBills();
            LocalDate today = LocalDate.now();
            String todayMonth = today.format(DateTimeFormatter.ofPattern("MM"));

            for (Bill bill : bills) {
                // Assuming bill.getDate() returns a date in the format "dd/MM/yyyy"
                String billDate = bill.getDate();
                if (billDate.length() >= 5) { // Ensure the string is long enough
                    String month = billDate.substring(3, 5);
                    if (todayMonth.equals(month)) {
                        revenue += bill.getProductExpense();
                    }
                }
            }

            // Convert revenue to millions and display
            revenue /= 1000000;
            String revenueStr = String.format("%.2f", revenue).concat(" tr.đ");
            JLabel revenueNum = new JLabel(revenueStr, SwingConstants.CENTER);
            revenueNum.setFont(new Font("Tahoma", Font.PLAIN, 40));
            revenueNum.setBounds(111, 43, 189, 47);
            revenuePanel.add(revenueNum);

            // Refresh the panel to show the new label
            revenuePanel.revalidate();
            revenuePanel.repaint();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
