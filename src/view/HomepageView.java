package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomepageView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomepageView frame = new HomepageView();
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
	public HomepageView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton qlySachBtn = new JButton("Quản lý sách");
		qlySachBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookManagementView bmv = new BookManagementView();
				bmv.setVisible(true);
				dispose();
			}
		});
		qlySachBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		qlySachBtn.setBounds(80, 25, 221, 50);
		contentPane.add(qlySachBtn);
		
		JButton qlHoaDonbtn = new JButton("Quản lý hóa đơn");
		qlHoaDonbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BillManagementView bmmv = new BillManagementView();
				bmmv.setVisible(true);
				dispose();
			}
		});
		qlHoaDonbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		qlHoaDonbtn.setBounds(381, 25, 221, 50);
		contentPane.add(qlHoaDonbtn);
		
		JButton qlyTaiKhoanKHbtn = new JButton("Quản lý tài khoản");
		qlyTaiKhoanKHbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		qlyTaiKhoanKHbtn.setBounds(682, 25, 221, 50);
		contentPane.add(qlyTaiKhoanKHbtn);
		
		JButton chartsbtn = new JButton("Thống kê");
		chartsbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chartsbtn.setBounds(983, 25, 221, 50);
		contentPane.add(chartsbtn);
	}
}