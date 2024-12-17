package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import dao.BookDao;
import dao.CategoryDAO; // Ensure you have this imported
import model.Book;
import model.Category;
import util.GenNewId;

public class ProductView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nameTf;
    private JTextField qtyTf;
    private JTextField priceTf;
    private JTextField authorTf;
    private JComboBox<Category> categoryComboBox; // JComboBox for categories // JComboBox for categories
    private JTextField descriptionTf;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ProductView frame = new ProductView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ProductView() {
        setTitle("Thêm Tác Phẩm Mới");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                new BookManagementView().setVisible(true);
            }
        });
        setBounds(100, 100, 500, 600);
        

        
        contentPane = new JPanel();
        contentPane.setForeground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        descriptionTf = new JTextField();
        descriptionTf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        descriptionTf.setBounds(195, 100, 245, 35);
        contentPane.add(descriptionTf);
        descriptionTf.setColumns(10);

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
        
        JLabel lblDescriptionTf = new JLabel("Mô tả");
        lblDescriptionTf.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblDescriptionTf.setBounds(49, 100, 111, 33);
        contentPane.add(lblDescriptionTf);

        JLabel lblName = new JLabel("Tên Sách");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblName.setBounds(49, 144, 95, 35);
        contentPane.add(lblName);

        JLabel lblQty = new JLabel("Số Lượng");
        lblQty.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblQty.setBounds(49, 189, 124, 35);
        contentPane.add(lblQty);

        JLabel lblPrice = new JLabel("Giá Tiền");
        lblPrice.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblPrice.setBounds(49, 234, 124, 35);
        contentPane.add(lblPrice);

        JLabel lblAuthor = new JLabel("Tác Giả");
        lblAuthor.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblAuthor.setBounds(49, 279, 111, 35);
        contentPane.add(lblAuthor);

        JLabel lblCategory = new JLabel("Thể Loại");
        lblCategory.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblCategory.setBounds(49, 326, 111, 33);
        contentPane.add(lblCategory);
        
        

        // Create JComboBox for categories
        categoryComboBox = new JComboBox<>();
        categoryComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        categoryComboBox.setBounds(195, 326, 245, 35);
        contentPane.add(categoryComboBox);

        // Populate categories into JComboBox
        populateCategories();

        JButton btnNewButton = new JButton("Lưu");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BookDao bd = new BookDao();
                int id = 0;
                try {
                    id = GenNewId.getNewBookId();
                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
                String name = nameTf.getText();
                String qtyStr = qtyTf.getText();
                int qty = Integer.parseInt(qtyStr);
                String priceStr = priceTf.getText();
                double price = Double.parseDouble(priceStr);
                String author = authorTf.getText();
                String description = descriptionTf.getText();
                Category selectedCategory = (Category) categoryComboBox.getSelectedItem(); // Get selected category
                

                Book newBook = new Book(id, name, qty, price, author, description, selectedCategory);
                try {
                    boolean success = bd.add(newBook);
                    if (success) {
                        JOptionPane.showMessageDialog(ProductView.this, "Thêm sản phẩm thành công!", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                        BookManagementView bmv = new BookManagementView();
                        bmv.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(ProductView.this, "Sản phẩm đã tồn tại!", "Thông báo",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } catch (ClassNotFoundException | IOException ex) {
                    JOptionPane.showMessageDialog(ProductView.this, "Lỗi khi thêm sản phẩm: " + ex.getMessage(), "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton.setBounds(329, 382, 111, 35);
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

        JLabel lblHeader = new JLabel("Thêm Tác Phẩm Mới");
        lblHeader.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblHeader.setBounds(121, 10, 232, 67);
        contentPane.add(lblHeader);
    }

    private void populateCategories() {
        CategoryDAO categoryDao = new CategoryDAO(); // Create an instance of CategoryDAO
        try {
            List<Category> categories = categoryDao.getAll(); // Fetch categories from the DAO
            for (Category category : categories) {
                categoryComboBox.addItem(category); // Add category names to JComboBox
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách thể loại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}