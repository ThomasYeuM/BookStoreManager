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
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;

import model.Category;
import dao.CategoryDAO;
import javax.swing.JTextArea;

public class CategoryView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idTf;
	private JTextField nameTf;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Thêm Thể Loại Mới");
		lblNewLabel.setBounds(124, 10, 211, 27);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		contentPane.add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel("Mã Thể Loại");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(38, 96, 118, 35);
		contentPane.add(lblNewLabel_1);
		JLabel lblNewLabel_1_1 = new JLabel("Tên Thể Loại");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(38, 176, 118, 35);
		contentPane.add(lblNewLabel_1_1);
		JLabel lblNewLabel_1_2 = new JLabel("Mô Tả");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(38, 256, 118, 35);
		contentPane.add(lblNewLabel_1_2);
		idTf = new JTextField();
		idTf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		idTf.setColumns(10);
		idTf.setBounds(211, 96, 244, 35);
		contentPane.add(idTf);
		nameTf = new JTextField();
		nameTf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameTf.setColumns(10);
		nameTf.setBounds(211, 176, 244, 35);
		contentPane.add(nameTf);
		JTextArea textArea = new JTextArea();
		textArea.setBounds(211, 256, 244, 128);
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
		cancelBtn.setBounds(211, 394, 111, 35);
		contentPane.add(cancelBtn);
		JButton saveBtn = new JButton("Lưu");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String idStr = idTf.getText().trim();
					CategoryDAO cateDao = new CategoryDAO();
					List<Category> categories = cateDao.getAll();
					for (Category category : categories) {
						if (Integer.parseInt(idStr) == category.getId()) {
							JOptionPane.showMessageDialog(CategoryView.this, "Mã Thể Loại đã tồn tại!",
									"Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					
					if (idStr.isEmpty()) {
						JOptionPane.showMessageDialog(CategoryView.this, "Mã Thể Loại không được để trống!",
								"Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (!idStr.matches("\\d+")) { 
						JOptionPane.showMessageDialog(CategoryView.this, "Mã Thể Loại phải là số nguyên dương!",
								"Lỗi Nhập Liệu", JOptionPane.ERROR_MESSAGE);
						return;
					}
					int id = Integer.parseInt(idStr);
					String name = nameTf.getText().trim();
					if (name.isEmpty() || name.length() < 3) {
						JOptionPane.showMessageDialog(CategoryView.this,
								"Tên Thể Loại không được để trống và phải có ít nhất 3 ký tự!", "Lỗi Nhập Liệu",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					String des = textArea.getText().trim();
					Category newCate = new Category(id, name, des);

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
		saveBtn.setBounds(344, 394, 111, 35);
		contentPane.add(saveBtn);
	}
}
