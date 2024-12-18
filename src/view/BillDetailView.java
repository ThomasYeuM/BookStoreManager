package view;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Bill;
import model.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class BillDetailView extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;

    public BillDetailView(Bill bill) {
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

        String[] columnNames = { "Tác Phẩm", "Số Lượng", "Giá" };
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 120, 560, 200);
        contentPane.add(scrollPane);

        // Thêm danh sách sách từ hóa đơn vào bảng
        HashMap<Book, Integer> books = bill.getBooks();
        for (Book book : books.keySet()) {
            Vector<Object> row = new Vector<>();
            row.add(book.getName());
            row.add(books.get(book));
            row.add(book.getPrice());
            tableModel.addRow(row);
        }

        JLabel totalLabel = new JLabel("Tổng Tiền: " + bill.getProductExpense());
        totalLabel.setBounds(400, 330, 150, 20);
        contentPane.add(totalLabel);
    }
}
