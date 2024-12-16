package view;

import model.Book;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class BookManagementView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				BookManagementView frame = new BookManagementView();
				frame.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public BookManagementView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        // Custom close actions
		        int response = JOptionPane.showConfirmDialog(
		            null, 
		            "Bạn có chắc muốn thoát quản lý sách?", 
		            "Confirm", 
		            JOptionPane.YES_NO_OPTION
		        );
		        
		        if (response == JOptionPane.YES_OPTION) {
		            dispose(); 
		            new HomepageView().setVisible(true);
		            
		        }
		    }
		});

		
		setBounds(100, 100, 888, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Danh sách sản phẩm");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitle.setBounds(312, 10, 249, 87);
		contentPane.add(lblTitle);

		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 100, 844, 400);
		contentPane.add(scrollPane);

		loadBookData();

		JButton backBtn = new JButton("Trang chủ");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HomepageView hpv = new HomepageView();
				hpv.setVisible(true);
				
			}
		});
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		backBtn.setBounds(733, 510, 131, 43);
		contentPane.add(backBtn);

		JButton addNewBookBtn = new JButton("Thêm Sách Mới");
		addNewBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductView pd = new ProductView();
				pd.setVisible(true);
				loadBooksFromFile();
			}
		});
		addNewBookBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addNewBookBtn.setBounds(20, 510, 156, 43);
		contentPane.add(addNewBookBtn);

		JButton suaSachBtn = new JButton("Sửa Tác Phẩm");
		suaSachBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		suaSachBtn.setBounds(199, 510, 156, 43);
		contentPane.add(suaSachBtn);

		JButton deleBtn = new JButton("Xóa Tác Phẩm");
		deleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSelectedBook();
			}
		});
		deleBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		deleBtn.setBounds(378, 510, 156, 43);
		contentPane.add(deleBtn);
		
		
	}

	private void loadBookData() {
		String[] columnNames = { "ID", "Tên Sách", "Số Lượng", "Giá", "Tác Giả", "Mô Tả" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/db/books.txt"))) {
			@SuppressWarnings("unchecked")
			ArrayList<Book> books = (ArrayList<Book>) ois.readObject();
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

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi đọc file books.txt!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}

		table.setModel(model);

		table.setFont(new Font("Tahoma", Font.PLAIN, 14));

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
	}

	private ArrayList<Book> loadBooksFromFile() {
		ArrayList<Book> books = new ArrayList<>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/db/books.txt"))) {
			books = (ArrayList<Book>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return books;
	}

	private void deleteSelectedBook() {
		int selectedRow = table.getSelectedRow();

		if (selectedRow != -1) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.removeRow(selectedRow);

			ArrayList<Book> books = loadBooksFromFile();

			books.remove(selectedRow);

			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/db/books.txt"))) {
				oos.writeObject(books);
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Lỗi khi ghi dữ liệu vào file!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}

			JOptionPane.showMessageDialog(this, "Sản phẩm đã được xóa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

}
