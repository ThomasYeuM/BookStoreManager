package view;

import javax.swing.*;
import javax.swing.border.Border;

import dao.CategoryDAO;
import model.Category;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CategoryEditView extends JFrame {

    private JTextField nameTf;
    private JTextArea textArea;
    private JButton saveButton;
    private Category category;

    public CategoryEditView(Category categoryToEdit) {
        this.category = categoryToEdit;

        setTitle("Chỉnh Sửa Thể Loại");
        setBounds(100, 100, 462, 486);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // Tiêu đề
        JLabel lblNewLabel = new JLabel("Sửa Thể Loại Sách");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel.setBounds(114, 25, 220, 61);
        getContentPane().add(lblNewLabel);

        // Mã thể loại (read-only)
        JLabel lblMThLoi = new JLabel("Mã thể loại:");
        lblMThLoi.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblMThLoi.setBounds(30, 100, 147, 25);
        getContentPane().add(lblMThLoi);

        JTextField idTf = new JTextField(String.valueOf(categoryToEdit.getId()));
        idTf.setBounds(216, 100, 200, 25);
        idTf.setEditable(false); // Không cho phép chỉnh sửa mã
        getContentPane().add(idTf);

        // Tên thể loại
        JLabel lblName = new JLabel("Tên Thể Loại:");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblName.setBounds(30, 150, 134, 25);
        getContentPane().add(lblName);

        nameTf = new JTextField(categoryToEdit.getName());
        nameTf.setBounds(216, 150, 200, 25);
        getContentPane().add(nameTf);

        // Mô tả
        JLabel lblDescription = new JLabel("Mô Tả:");
        lblDescription.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblDescription.setBounds(30, 200, 100, 25);
        getContentPane().add(lblDescription);

        textArea = new JTextArea(categoryToEdit.getDescription());
        textArea.setBounds(216, 200, 200, 150);
        Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
        textArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        getContentPane().add(textArea);

        // Nút Lưu
        saveButton = new JButton("Lưu");
        saveButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        saveButton.setBounds(326, 394, 90, 30);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCategory();
            }
        });
        getContentPane().add(saveButton);

        // Nút Hủy
        JButton cancelButton = new JButton("Hủy");
        cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cancelButton.setBounds(216, 394, 90, 30);
        cancelButton.addActionListener(e -> dispose());
        getContentPane().add(cancelButton);
    }

    private void saveCategory() {
        String name = nameTf.getText().trim();
        String des = textArea.getText().trim();

        // Kiểm tra dữ liệu nhập
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên thể loại không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (des.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mô tả không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            category.setName(name);
            category.setDescription(des);

            CategoryDAO categoryDao = new CategoryDAO();
            categoryDao.update(category);

            JOptionPane.showMessageDialog(this, "Sửa thể loại thành công!", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);

            dispose();
            CategoryManagementView managementView = new CategoryManagementView();
            managementView.setVisible(true);

        } catch (ClassNotFoundException | IOException ex) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi cập nhật dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // Phương thức test giao diện
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // Tạo một đối tượng Category giả lập để chỉnh sửa
                Category dummyCategory = new Category(1, "Sách Khoa Học", "Các sách về khoa học");
                CategoryEditView frame = new CategoryEditView(dummyCategory);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
