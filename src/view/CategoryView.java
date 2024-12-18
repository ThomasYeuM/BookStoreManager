package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;

import model.Category;
import util.GenNewId;
import dao.CategoryDAO;
import javax.swing.JTextArea;

public class CategoryView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameTf;  // Chỉ cần ô nhập tên thể loại
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryView frame = new CategoryView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CategoryView() {

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Show confirmation dialog

				// Close current window
				dispose();

				// Open HomepageView
				new CategoryManagementView().setVisible(true);

			}
		});
		setBounds(100, 100, 479, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Thêm Thể Loại Mới");
		lblNewLabel.setBounds(124, 10, 211, 27);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên Thể Loại");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(38, 96, 118, 35);
		contentPane.add(lblNewLabel_1_1);
		JLabel lblNewLabel_1_2 = new JLabel("Mô Tả");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(38, 176, 118, 35);
		contentPane.add(lblNewLabel_1_2);
		
		nameTf = new JTextField();
		nameTf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameTf.setColumns(10);
		nameTf.setBounds(211, 96, 244, 35);
		contentPane.add(nameTf);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(211, 176, 244, 128);
		Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
		textArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		contentPane.add(textArea);
		
		JButton cancelBtn = new JButton("Hủy");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoryManagementView cate = new CategoryManagementView();
				cate.setVisible(true);
				dispose();

			}
		});
		cancelBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		cancelBtn.setBounds(211, 340, 111, 35);
		contentPane.add(cancelBtn);
		
		JButton saveBtn = new JButton("Lưu");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Lấy mã thể loại mới tự động
					int id = GenNewId.getNewCategoryId();  // Sử dụng phương thức trong lớp GenNewId

					String name = nameTf.getText().trim();
					if (name.isEmpty() || name.length() < 3) {
						JOptionPane.showMessageDialog(CategoryView.this,
								"Tên Thể Loại không được để trống và phải có ít nhất 3 ký tự!", "Lỗi Nhập Liệu",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					String des = textArea.getText().trim();
					Category newCate = new Category(id, name, des);

					CategoryDAO cateDao = new CategoryDAO();
					cateDao.add(newCate);
					JOptionPane.showMessageDialog(CategoryView.this, "Thêm thể loại mới thành công!", "Thông Báo",
							JOptionPane.INFORMATION_MESSAGE);
					CategoryManagementView cmv = new CategoryManagementView();
					cmv.setVisible(true);
					dispose();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(CategoryView.this, "Lỗi định dạng dữ liệu nhập vào!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				} catch (ClassNotFoundException | IOException ex) {
					JOptionPane.showMessageDialog(CategoryView.this, "Đã xảy ra lỗi khi lưu thể loại!", "Lỗi Hệ Thống",
							JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});
		saveBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		saveBtn.setBounds(344, 340, 111, 35);
		contentPane.add(saveBtn);
	}
}
