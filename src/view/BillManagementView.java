package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class BillManagementView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

	private static String Username;
    /**
     * Launch the application.
     */
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

    /**
     * Create the frame.
     */
    public BillManagementView(String Username) {
    	this.Username = Username;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 882, 559);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Quản Lý Hóa Đơn");
        lblNewLabel.setBounds(335, 10, 198, 116);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        contentPane.add(lblNewLabel);
        
        // Tạo nút "Tạo Hóa Đơn Mới"
        JButton createBillBtn = new JButton("Tạo Hóa Đơn Mới");
        createBillBtn.setBounds(698, 482, 160, 30);
        createBillBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        createBillBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new BillView().setVisible(true);
                createNewBill();
            }
        });

        contentPane.add(createBillBtn);
        
        // Tạo bảng hiển thị hóa đơn
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 93, 858, 379);
        contentPane.add(scrollPane);
        
        String[] columnNames = { "Mã Hóa Đơn", "Tên Khách Hàng", "Người Tạo", "Ngày Tạo", "Tổng Giá Tiền" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table.setModel(model);
    }
    
    // Hàm tạo hóa đơn mới
    private void createNewBill() {
    	System.out.print(Username);
    }

	
}
