package view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import dao.BookDao;
import model.Book;

public class ProductView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField idTf;
    private JTextField nameTf;
    private JTextField qtyTf;
    private JTextField priceTf;
    private JTextField authorTf;
    private JTextField categoryTf;

    // Giả sử bạn có một JList hoặc bảng để hiển thị danh sách sản phẩm
    private DefaultListModel<String> listModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProductView frame = new ProductView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ProductView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 488, 494);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        idTf = new JTextField();
        idTf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        idTf.setBounds(195, 99, 244, 35);
        contentPane.add(idTf);
        idTf.setColumns(10);

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

        JLabel lblNewLabel = new JLabel("ID");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(49, 99, 95, 35);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Tên Sách");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1.setBounds(49, 144, 95, 35);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Số Lượng");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1_1.setBounds(49, 189, 124, 35);
        contentPane.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("Giá Tiền");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1_2.setBounds(49, 234, 124, 35);
        contentPane.add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_3 = new JLabel("Tác Giả");
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1_3.setBounds(49, 279, 111, 35);
        contentPane.add(lblNewLabel_1_3);

        JLabel lblNewLabel_1_4 = new JLabel("Thể Loại");
        lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1_4.setBounds(49, 326, 111, 33);
        contentPane.add(lblNewLabel_1_4);

        JButton btnNewButton = new JButton("Lưu");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                BookDao bd = new BookDao();
                String idStr = idTf.getText();
                int id = Integer.parseInt(idStr);
                String name = nameTf.getText();
                String qtyStr = qtyTf.getText();
                int qty = Integer.parseInt(qtyStr);
                String priceStr = priceTf.getText();
                double price = Double.parseDouble(priceStr);
                String author = authorTf.getText();
                String category = categoryTf.getText();
                Book newBook = new Book(id, name, qty, price, author, category);
                try {
                    boolean success = bd.add(newBook);
                    if (success) {
                        JOptionPane.showMessageDialog(ProductView.this, "Thêm sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        BookManagementView bmv = new BookManagementView();
                        bmv.setVisible(true);
                        dispose();
                        
                        
                    } else {
                        JOptionPane.showMessageDialog(ProductView.this, "Sản phẩm đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (ClassNotFoundException | IOException ex) {
                    JOptionPane.showMessageDialog(ProductView.this, "Lỗi khi thêm sản phẩm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton.setBounds(328, 382, 111, 35);
        contentPane.add(btnNewButton);

        JButton cancelBtn = new JButton("Hủy");
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancelBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        cancelBtn.setBounds(195, 382, 111, 35);
        contentPane.add(cancelBtn);

        JLabel lblNewLabel_2 = new JLabel("Thêm Tác Phẩm Mới");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblNewLabel_2.setBounds(121, 10, 232, 67);
        contentPane.add(lblNewLabel_2);

        categoryTf = new JTextField();
        categoryTf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        categoryTf.setBounds(195, 328, 245, 35);
        contentPane.add(categoryTf);
        categoryTf.setColumns(10);
    }

}
