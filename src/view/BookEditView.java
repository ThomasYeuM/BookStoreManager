package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dao.BookDao;
import dao.CategoryDAO;
import model.Book;
import model.Category;

import java.util.List;

public class BookEditView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int editBooKid;
	private JTextField nameTf;
	private JTextField qtyTf;
	private JTextField priceTf;
	private JTextField authorTf;
	private JTextField descriptionTf; // New JTextField for description
	private JComboBox<Category> categoryComboBox; // JComboBox for categories

	BookDao bookDao = new BookDao();
	CategoryDAO categoryDao = new CategoryDAO(); // Assuming you have a CategoryDAO

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				BookEditView frame = new BookEditView();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public BookEditView() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				new BookManagementView().setVisible(true);
			}
		});
		setBounds(100, 100, 500, 600); // Increased height for better layout
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nameTf = new JTextField();
		nameTf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameTf.setColumns(10);
		nameTf.setBounds(195, 144, 244, 35);
		contentPane.add(nameTf);

		qtyTf = new JTextField();
		qtyTf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		qtyTf.setColumns(10);
		qtyTf.setBounds(195, 189, 124, 35);
		contentPane.add(qtyTf);

		priceTf = new JTextField();
		priceTf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		priceTf.setColumns(10);
		priceTf.setBounds(195, 234, 244, 35);
		contentPane.add(priceTf);

		authorTf = new JTextField();
		authorTf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		authorTf.setBounds(195, 279, 245, 35);
		contentPane.add(authorTf);
		authorTf.setColumns(10);

		descriptionTf = new JTextField(); // Initialize the JTextField for description
		descriptionTf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		descriptionTf.setBounds(195, 324, 244, 35); // Set bounds for description field
		contentPane.add(descriptionTf);
		descriptionTf.setColumns(10);
	
		
		JLabel lblName = new JLabel("Tên Sách");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName.setBounds(49, 144, 95, 35);
		contentPane.add(lblName);

		JLabel lblQty = new JLabel("Số Lượng");
		lblQty.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblQty.setBounds(49, 189, 124, 35);
		contentPane.add(lblQty);

		JLabel lblPrice = new JLabel("Giá Tiền");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrice.setBounds(49, 234, 124, 35);
		contentPane.add(lblPrice);

		JLabel lblAuthor = new JLabel("Tác Giả");
		lblAuthor.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAuthor.setBounds(49, 279, 111, 35);
		contentPane.add(lblAuthor);

		JLabel lblDescription = new JLabel("Mô Tả"); // Label for description
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDescription.setBounds(49, 324, 111, 35);
		contentPane.add(lblDescription);

		JLabel lblCategory = new JLabel("Thể Loại");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCategory.setBounds(49, 369, 111, 33);
		contentPane.add(lblCategory);

		// Create JComboBox for categories
		categoryComboBox = new JComboBox<>();
		categoryComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		categoryComboBox.setBounds(195, 369, 245, 35);
		contentPane.add(categoryComboBox);

		JButton saveButton = new JButton("Lưu");
		saveButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		saveButton.setBounds(329, 420, 111, 35);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = editBooKid;
				String name = nameTf.getText();
				int qty = Integer.parseInt(qtyTf.getText());
				double price = Double.parseDouble(priceTf.getText());
				String author = authorTf.getText();
				String description = descriptionTf.getText(); // Get the description
				Category selectedCategory = (Category) categoryComboBox.getSelectedItem(); // Get selected category

				Book bookEdited = new Book(id, name, qty, price, author, description, selectedCategory);
				try {
					bookDao.update(bookEdited);
					JOptionPane.showMessageDialog(BookEditView.this, "Sửa sách thành công!", "Thông Báo",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new BookManagementView().setVisible(true);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(BookEditView.this, "Lỗi: Không tìm thấy lớp", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(BookEditView.this, "Lỗi: Không thể lưu dữ liệu", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		contentPane.add(saveButton);

		JButton cancelBtn = new JButton("Hủy");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		cancelBtn.setBounds(195, 420, 111, 35);
		contentPane.add(cancelBtn);

		JLabel lblEditBook = new JLabel("Sửa Tác Phẩm");
		lblEditBook.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblEditBook.setBounds(121, 10, 250, 50);
		contentPane.add(lblEditBook);

		// Populate categories into JComboBox
		populateCategories();
	}

	private void populateCategories() {
		try {
			List<Category> categories = categoryDao.getAll(); // Assuming this method returns a list of categories
			for (Category category : categories) {
				categoryComboBox.addItem(category); // Add category objects to JComboBox
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách thể loại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	public BookEditView(Book bookToEdit) {
		this();
		this.setVisible(true);
		this.editBooKid = bookToEdit.getId();

		this.nameTf.setText(bookToEdit.getName());
		this.qtyTf.setText(String.valueOf(bookToEdit.getQty()));
		this.priceTf.setText(String.valueOf(bookToEdit.getPrice()));
		this.authorTf.setText(bookToEdit.getAuthor());
		this.descriptionTf.setText(bookToEdit.getDescription()); // Set description
		this.categoryComboBox.setSelectedItem(bookToEdit.getCategory()); // Set selected category
	}
}