package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.BookController;
import dao.BookDao;
import model.Book;
import util.ImageUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Color;

public class BookMangementView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane sp;
	private ImageUtils imageUtils; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookMangementView frame = new BookMangementView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public BookMangementView() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Danh sách sản phẩm");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 0, 315, 77);
		contentPane.add(lblNewLabel);
		
		
		//Handle table
		BookDao bookDao = new BookDao();
		BookController bookController = new BookController(bookDao);		
		String[][] data = {};
		String[] columnNames = { "ID", "Tên", "Số lượng", "Giá", "Tác giả", "Mô tả" };
	
        // Initializing the JTable
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        try {
			List<Book> listBook = bookController.getAllBooks();
			for (Book book : listBook) {
				model.addRow(new Object[]{book.getId(), book.getName(), book.getName(), book.getQty(), book.getPrice(), book.getAuthor().getName(), book.getDescription()});
	        }
			System.out.println(listBook.get(0).getClass());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        table.setRowHeight(32);
        table.setMinimumSize(new Dimension(60, 60));
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));
        
		table.setBounds(20, 83, 557, 199);
		sp = new JScrollPane(table);
		sp.setBounds(10, 71, 764, 465);
		contentPane.add(sp);
		
		JButton addBookButton = new JButton("Thêm sách");
		addBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String action = e.getActionCommand();
				System.out.println(action);
				AddOrUpdateBookView AODview = new AddOrUpdateBookView(action);
				AODview.setVisible(true);
				
				
				
			}
		});
		addBookButton.setBackground(new Color(255, 255, 255));
		addBookButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		addBookButton.setBounds(784, 71, 183, 60);
		contentPane.add(addBookButton);
		
		
		
	}
}
