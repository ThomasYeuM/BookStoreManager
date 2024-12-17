package view;

import model.Book;
import model.Category;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.BookDao;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class BookManagementView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    BookDao bookDAO = new BookDao();
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
    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	addWindowListener(new WindowAdapter() {
    	    @Override
    	    public void windowClosing(WindowEvent e) {
    	        // Show confirmation dialog
    	        if (JOptionPane.showConfirmDialog(
    	            null, 
    	            "Do you really want to exit?", 
    	            "Confirm", 
    	            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    	            
    	            // Close current window
    	            dispose();
    	            
    	            // Open HomepageView
//    	            new HomepageView().setVisible(true);
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

 
        
        JButton doneBtn = new JButton("Xong");
		doneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				 new HomepageView("").setVisible(true);
			}
		});
		doneBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		doneBtn.setBounds(733, 510, 131, 43);
		contentPane.add(doneBtn);
        
        JButton addNewBookBtn = new JButton("Thêm Sách Mới");
        addNewBookBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		ProductView pd =  new ProductView();
        		pd.setVisible(true);
        	}
        });
        addNewBookBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        addNewBookBtn.setBounds(20, 510, 156, 43);
        contentPane.add(addNewBookBtn);
        
        JButton suaSachBtn = new JButton("Sửa Tác Phẩm");
        suaSachBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        suaSachBtn.setBounds(199, 510, 156, 43);
        suaSachBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {



				editSelectedBook();
				
			}
		});
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
        String[] columnNames = { "ID", "Tên Sách", "Số Lượng", "Giá", "Tác Giả","Thể loại", "Mô Tả" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        List<Book> books = null;
		try {
			books = bookDAO.getAll();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách thể loại!", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
            for (Book book : books) {
                Vector<Object> row = new Vector<>();
                row.add(book.getId());
                row.add(book.getName());
                row.add(book.getQty());
                row.add(book.getPrice());
                row.add(book.getAuthor());
                row.add(book.getCategory().getName());
                row.add(book.getDescription());
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

	private void deleteSelectedBook() {
		int selectedRow = table.getSelectedRow();

		if (selectedRow != -1) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();

			List<Book> books = null;
			try {
				books = bookDAO.getAll();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách thể loại!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (selectedRow < books.size()) {
				Book bookToDelete = books.get(selectedRow);

				try {
					bookDAO.delete(bookToDelete);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Lỗi khi xóa thể loại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}

				model.removeRow(selectedRow);

				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/db/books.txt"))) {
					oos.writeObject(books);
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
	
	private void editSelectedBook() {
		int selectedRow = table.getSelectedRow();
		
		if (selectedRow != -1) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();

			List<Book> books = null;
			try {
				books = bookDAO.getAll();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách sách!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Book bookToEdit = books.get(selectedRow);
			System.out.println(bookToEdit.getId());
			BookEditView bookEditView = new BookEditView(bookToEdit);
			bookEditView.setVisible(true);
			this.dispose();
			
			
		}
		
//		Category selectedCategory = 
	}

}