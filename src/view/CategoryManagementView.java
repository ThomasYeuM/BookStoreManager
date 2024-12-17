package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.CategoryDAO;
import model.Book;
import model.Category;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import view.CategoryEditView;
public class CategoryManagementView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	CategoryDAO categoryDao = new CategoryDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryManagementView frame = new CategoryManagementView();
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
	public CategoryManagementView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblTitle = new JLabel("Thể Loại Tác Phẩm");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitle.setBounds(266, 10, 249, 87);
		contentPane.add(lblTitle);

		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 100, 752, 397);
		contentPane.add(scrollPane);

		JButton addCateBtn = new JButton("Thêm Thể Loại");
		addCateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoryView categoryView = new CategoryView();
				categoryView.setVisible(true);
				dispose();
			}
		});
		addCateBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addCateBtn.setBounds(20, 507, 150, 44);
		contentPane.add(addCateBtn);

		JButton editCateBtn = new JButton("Sửa Thể Loại");
		editCateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editCategory();
				
			}
		});
		editCateBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editCateBtn.setBounds(202, 507, 150, 44);
		contentPane.add(editCateBtn);

		JButton deleteCateBtn = new JButton("Xóa Thể Loại");
		deleteCateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSelectedCategory();
			}
		});
		deleteCateBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		deleteCateBtn.setBounds(393, 507, 150, 44);
		contentPane.add(deleteCateBtn);

		JButton doneBtn = new JButton("Xong");
		doneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		doneBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		doneBtn.setBounds(622, 507, 150, 44);
		contentPane.add(doneBtn);
		loadCategoryData();
	}

	private void loadCategoryData() {
		String[] columnNames = { "ID", "Thể Loại", "Mô Tả" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		List<Category> categories = null;
		try {
			categories = categoryDao.getAll();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách thể loại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}

		for (Category category : categories) {
			Vector<Object> row = new Vector<>();
			row.add(category.getId());
			row.add(category.getName());
			row.add(category.getDescription());
			model.addRow(row);
		}

		table.setModel(model);

		table.setFont(new Font("Tahoma", Font.PLAIN, 14));

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
	}

	private void deleteSelectedCategory() {
		int selectedRow = table.getSelectedRow();

		if (selectedRow != -1) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();

			List<Category> categories = null;
			try {
				categories = categoryDao.getAll();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách thể loại!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (selectedRow < categories.size()) {
				Category categoryToDelete = categories.get(selectedRow);

				try {
					categoryDao.delete(categoryToDelete);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Lỗi khi xóa thể loại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}

				model.removeRow(selectedRow);

				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/db/categories.txt"))) {
					oos.writeObject(categories);
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Lỗi khi ghi dữ liệu vào file!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				JOptionPane.showMessageDialog(this, "Thể loại đã được xóa!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy thể loại trong danh sách!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một thể loại để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void editCategory() {
		int selectedRow = table.getSelectedRow();
		
		if (selectedRow != -1) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();

			List<Category> categories = null;
			try {
				categories = categoryDao.getAll();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách thể loại!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Category categoryToEdit = categories.get(selectedRow);
			System.out.println(categoryToEdit.getDescription());
			CategoryEditView categoryEditView = new CategoryEditView(categoryToEdit);
			categoryEditView.setVisible(true);
			
			
		}
		
//		Category selectedCategory = 
	}

}
