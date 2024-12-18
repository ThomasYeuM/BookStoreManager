package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.CategoryDAO;
import model.Category;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CategoryManagementView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private CategoryDAO categoryDao = new CategoryDAO();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CategoryManagementView frame = new CategoryManagementView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public CategoryManagementView() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(
                        null,
                        "Bạn có chắc muốn thoát?",
                        "Xác nhận",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        setBounds(100, 100, 796, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Thể Loại Tác Phẩm");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitle.setBounds(266, 10, 249, 87);
        contentPane.add(lblTitle);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 100, 752, 397);
        contentPane.add(scrollPane);

        JButton addCateBtn = new JButton("Thêm Thể Loại");
        addCateBtn.addActionListener(e -> openAddCategoryView());
        addCateBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        addCateBtn.setBounds(20, 507, 150, 44);
        contentPane.add(addCateBtn);

        JButton editCateBtn = new JButton("Sửa Thể Loại");
        editCateBtn.addActionListener(e -> editCategory());
        editCateBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        editCateBtn.setBounds(202, 507, 150, 44);
        contentPane.add(editCateBtn);

        JButton deleteCateBtn = new JButton("Xóa Thể Loại");
        deleteCateBtn.addActionListener(e -> deleteSelectedCategory());
        deleteCateBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        deleteCateBtn.setBounds(393, 507, 150, 44);
        contentPane.add(deleteCateBtn);

        JButton doneBtn = new JButton("Xong");
        doneBtn.addActionListener(e -> dispose());
        doneBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        doneBtn.setBounds(622, 507, 150, 44);
        contentPane.add(doneBtn);

        loadCategoryData();
    }

    private void loadCategoryData() {
        String[] columnNames = { "ID", "Thể Loại", "Mô Tả" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try {
            List<Category> categories = categoryDao.getAll();
            for (Category category : categories) {
                Vector<Object> row = new Vector<>();
                row.add(category.getId());
                row.add(category.getName());
                row.add(category.getDescription());
                model.addRow(row);
            }
        } catch (ClassNotFoundException | IOException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách thể loại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        table.setModel(model);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void openAddCategoryView() {
        new CategoryView().setVisible(true);
        dispose();
    }

    private void editCategory() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {
            try {
                List<Category> categories = categoryDao.getAll();
                if (selectedRow < categories.size()) {
                    Category categoryToEdit = categories.get(selectedRow);
                    CategoryEditView categoryEditView = new CategoryEditView(categoryToEdit);
                    categoryEditView.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy thể loại để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ClassNotFoundException | IOException e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách thể loại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một thể loại để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedCategory() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {
            try {
                List<Category> categories = categoryDao.getAll();
                if (selectedRow < categories.size()) {
                    Category categoryToDelete = categories.get(selectedRow);
                    categoryDao.delete(categoryToDelete);
                    loadCategoryData();
                    JOptionPane.showMessageDialog(this, "Thể loại đã được xóa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy thể loại để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ClassNotFoundException | IOException e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa thể loại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một thể loại để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
