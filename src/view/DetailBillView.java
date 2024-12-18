package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.Bill;
import model.Book;

public class DetailBillView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Bill bill;
	
	private JTable table;
	private DefaultTableModel tableModel;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetailBillView frame = new DetailBillView(bill);
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
	public DetailBillView(Bill bill) {    

        setTitle("Chi Tiết Hóa Đơn");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Chi Tiết Hóa Đơn");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        titleLabel.setBounds(200, 10, 200, 30);
        contentPane.add(titleLabel);

        JLabel customerLabel = new JLabel("Tên Khách Hàng: " + bill.getCustomerName());
        customerLabel.setBounds(10, 50, 300, 20);
        contentPane.add(customerLabel);

        JLabel dateLabel = new JLabel("Ngày Tạo: " + bill.getDate());
        dateLabel.setBounds(10, 80, 300, 20);
        contentPane.add(dateLabel);

        String[] columnNames = {"Tác Phẩm", "Số Lượng", "Giá"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 120, 560, 200);
        contentPane.add(scrollPane);

        HashMap<Book, Integer> books = bill.getBooks();
        if (books != null && !books.isEmpty()) {
            for (Book book : books.keySet()) {
                Vector<Object> row = new Vector<>();
                row.add(book.getName());
                row.add(books.get(book));
                row.add(book.getPrice());
                tableModel.addRow(row);
            }
        }


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }


        JLabel totalLabel = new JLabel("Tổng Tiền: " + bill.getProductExpense());
        totalLabel.setBounds(400, 330, 150, 20);
        contentPane.add(totalLabel);
    }
	

}
