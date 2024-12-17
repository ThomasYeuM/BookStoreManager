package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

import model.Bill;
import model.Book;


public class BillView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField getNameCusTf;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel totalAmountLabel;
    private static String Username;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BillView frame = new BillView();
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
    public BillView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 667, 601);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton addBillBtn = new JButton("Tạo Hóa Đơn");
        addBillBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BillManagementView frame = new BillManagementView(Username);
                frame.setVisible(true);
            }
        });
        addBillBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        addBillBtn.setBounds(445, 514, 170, 39);
        contentPane.add(addBillBtn);

        getNameCusTf = new JTextField();
        getNameCusTf.setBounds(266, 22, 262, 39);
        contentPane.add(getNameCusTf);
        getNameCusTf.setColumns(10);

        JLabel lblNewLabel = new JLabel("Tên Khách Hàng:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(30, 22, 199, 39);
        contentPane.add(lblNewLabel);

        JButton addBookBtn = new JButton("Thêm Tác Phẩm");
        addBookBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddBookDialog();
            }
        });
        addBookBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        addBookBtn.setBounds(35, 514, 170, 39);
        contentPane.add(addBookBtn);

        String[] columnNames = { "Tác Phẩm", "Số Lượng", "Giá Tiền" };
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 100, 643, 353);
        contentPane.add(scrollPane);

        table.setFont(new Font("Tahoma", Font.PLAIN, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        totalAmountLabel = new JLabel("Tổng Tiền: 0");
        totalAmountLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        totalAmountLabel.setBounds(445, 474, 208, 30);
        contentPane.add(totalAmountLabel);



        JButton removeBookBtn = new JButton("Xóa Tác Phẩm");
        removeBookBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        removeBookBtn.setBounds(240, 514, 170, 39);
        contentPane.add(removeBookBtn);

        removeBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelectedBook();
            }
        });

        setTitle("Danh sách sản phẩm sắp mua");
    }

    private void openAddBookDialog() {
        AddABookToBillView addBookView = new AddABookToBillView();
        addBookView.setVisible(true);

        addBookView.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                String bookName = addBookView.getSelectedBookName();
                int quantity = addBookView.getSelectedQuantity();
                double price = addBookView.getSelectedPrice();
                if (bookName != null && quantity > 0) {
                    addBookToTable(bookName, quantity, price * quantity);
                }
            }
        });
    }

    private void addBookToTable(String bookName, int quantity, double price) {
        Vector<Object> row = new Vector<>();
        row.add(bookName);
        row.add(quantity);
        row.add(price);
        tableModel.addRow(row);
        updateTotalAmount();
    }

    private double updateTotalAmount() {
        double totalAmount = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            totalAmount += (double) tableModel.getValueAt(i, 2);
        }
        totalAmountLabel.setText("Tổng Tiền: " + totalAmount);
        return totalAmount;
    }

    private void removeSelectedBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
            updateTotalAmount();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sách để xóa!");
        }
    }

    public String getCustomerName() {
    	return getNameCusTf.getText();
    }
    public double getAllBillPrice() {
    	return updateTotalAmount();
    }
}
