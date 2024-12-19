package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

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
		setBounds(100, 100, 685, 750);
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
                AccountManagementView amv = new AccountManagementView();
                amv.setVisible(true);

                

            }
        });
		qlyTaiKhoanKHbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		qlyTaiKhoanKHbtn.setBounds(418, 273, 221, 50);
		contentPane.add(qlyTaiKhoanKHbtn);
		
		JButton chartsbtn = new JButton("Thống kê");
		chartsbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chartsbtn.setBounds(125, 299, 221, 50);
		contentPane.add(chartsbtn);
		chartsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				Map<String, Double> revenue = new TreeMap<String, Double>();

				BillDAO billDAO = new BillDAO();
				Bill billController = new BillController(billDAO, null);
				try {
					List<Bill> bills = billController.getAllBills();

					for (int i = 1; i <= 12; i++) {
						String month = String.format("%02d", i);
						revenue.put(month, revenue.getOrDefault(month, 0.0));
					}

					for (Bill bill : bills) {
						String month = bill.getDate().substring(3, 5);
						revenue.put(month, revenue.getOrDefault(month, 0.0) + bill.getTotal());
					}

					for (Map.Entry<String, Double> entry : revenue.entrySet()) {
						dataset.addValue(entry.getValue(), "Doanh thu (đồng)", entry.getKey());
					}

					JFreeChart barChart = ChartFactory.createBarChart("Thống kê doanh thu", "Tháng", "Doanh thu (đồng)",
							dataset, PlotOrientation.VERTICAL, true, true, false);

					JFrame frame = new JFrame();
					frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					frame.setSize(960, 540);
					frame.getContentPane().add(new ChartPanel(barChart));
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
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
                AccountManagementView amv = new AccountManagementView();
                amv.setVisible(true);
                
            }
        });
		doiMatKhaubtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		doiMatKhaubtn.setBounds(400, 120, 221, 50);
		contentPane.add(doiMatKhaubtn);
		
		


	}

}
