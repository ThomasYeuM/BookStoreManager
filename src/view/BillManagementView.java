package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.BillDAO;
import model.Bill;
import model.Book;
import util.GenNewId;





    /**
     * Create the frame.
     */
    public class BillManagementView extends JFrame {
        public void loadBillData() {
            List<Bill> bills = null;
            try {
                bills = billDao.getAll(); // Lấy tất cả hóa đơn từ cơ sở dữ liệu
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }

            if (bills != null) {
                // Lặp qua danh sách hóa đơn và thêm vào bảng
                for (Bill bill : bills) {
                    Vector<Object> row = new Vector<>();
                    row.add(bill.getId()); // Mã hóa đơn
                    row.add(bill.getCustomerName()); // Tên khách hàng
                    row.add(bill.getAdminID()); // Người tạo
                    row.add(bill.getDate()); // Ngày tạo
                    row.add(bill.getProductExpense()); // Tổng giá tiền

                    model.addRow(row); // Thêm dòng vào bảng
                }
            }
        }
  
    	private static final long serialVersionUID = 1L;
    	private JPanel contentPane;
    	private JTable table;
    	private static DefaultTableModel model;
    	private static String Username; // Đổi thành biến không tĩnh
    	private BillView billView;
    	private BillDAO billDao; 
        public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        BillManagementView frame = new BillManagementView(Username);
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public BillManagementView(String Username) {
            this.Username = Username;
            billDao = new BillDAO();
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    if (JOptionPane.showConfirmDialog(null, "Do you really want to exit?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        dispose();
                    }
                }
            });

            setBounds(100, 100, 882, 559);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);

            JLabel lblTitle = new JLabel("Quản Lý Hóa Đơn");
            lblTitle.setBounds(335, 10, 198, 116);
            lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
            contentPane.add(lblTitle);

            JButton createBillBtn = new JButton("Tạo Hóa Đơn Mới");
            createBillBtn.setBounds(698, 482, 160, 30);
            createBillBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
            createBillBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    billView = new BillView(Username);
                    billView.setVisible(true);
                    dispose();
                }
            });
            contentPane.add(createBillBtn);

            String[] columnNames = {"Mã Hóa Đơn", "Tên Khách Hàng", "Người Tạo", "Ngày Tạo", "Tổng Giá Tiền"};
            model = new DefaultTableModel(columnNames, 0);

            table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(10, 93, 858, 379);
            contentPane.add(scrollPane);
            
            JButton chitietBill = new JButton("Xem chi tiết");
            chitietBill.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		
            	}
            });
            chitietBill.setFont(new Font("Tahoma", Font.PLAIN, 14));
            chitietBill.setBounds(10, 482, 160, 30);
            contentPane.add(chitietBill);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            loadBillData();
        }
    }
