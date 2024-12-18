package view;


import java.awt.Font;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.BookDao;
import model.Book;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddABookToBillView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JButton addBtn;
	private String selectedBookName;
	private int selectedQuantity;
	private double selectedPrice;
	private BookDao BookDAO = new BookDao();
	public String getSelectedBookName() {
		return selectedBookName;
	}

	public int getSelectedQuantity() {
		return selectedQuantity;
	}
	public double getSelectedPrice() {
		return selectedPrice;
	}

	public AddABookToBillView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 644, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 81, 610, 388);
		contentPane.add(scrollPane);

		loadBookData();
		setContentPane(contentPane);

		lblNewLabel = new JLabel("Thêm sản phẩm vào hóa đơn");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(168, 10, 294, 61);
		contentPane.add(lblNewLabel);

		addBtn = new JButton("Thêm");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBook();
			}
		});
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addBtn.setBounds(456, 479, 164, 46);
		contentPane.add(addBtn);
	}

	
	private void loadBookData() {
		String[] columnNames = { "ID", "Tên Sách", "Số Lượng", "Giá", "Tác Giả", "Mô Tả" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		List<Book> books;
		try {
			books = BookDAO.getAll();
			for (Book book : books) {
				Vector<Object> row = new Vector<>();
				row.add(book.getId());
				row.add(book.getName());
				row.add(book.getQty());
				row.add(book.getPrice());
				row.add(book.getAuthor());
				row.add(book.getDescription());
				model.addRow(row);
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách sách!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}

		table.setModel(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
	}

	private void addBook() {
		int selectedIndex = table.getSelectedRow();
		List<Book> book = null;
		try {
			book = BookDAO.getAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Book selectedBook = book.get(selectedIndex);
		if (selectedIndex == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một cuốn sách để thêm!", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		selectedBookName = table.getValueAt(selectedIndex, 1).toString();
		 selectedPrice = (Double) table.getValueAt(selectedIndex, 3);
		Quantity qtyFrame = new Quantity();
		qtyFrame.setVisible(true);
		
		qtyFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosed(java.awt.event.WindowEvent e) {
				selectedQuantity = qtyFrame.getQuantity(); 
				if ( selectedQuantity > selectedBook.getQty()) {
					JOptionPane.showMessageDialog(AddABookToBillView.this, "Số lượng sách không đủ, vui lòng mua ít hơn!", "Thông báo",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (selectedQuantity <= 0) {
					JOptionPane.showMessageDialog(AddABookToBillView.this, "Số lượng phải lớn hơn 0!", "Thông báo",
							JOptionPane.WARNING_MESSAGE);
					return;
				}


				JOptionPane.showMessageDialog(AddABookToBillView.this, "Thêm sách thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				selectedBook.setQty(selectedBook.getQty() - selectedQuantity);
				try {
					BookDAO.update(selectedBook);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
	}

}
