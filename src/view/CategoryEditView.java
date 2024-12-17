package view;
 
import javax.swing.*;

import model.Category;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;

public class CategoryEditView extends JFrame {

    private JTextField nameTf;
    private JButton saveButton;
    private Category category;
    private JTextField idTf;
    private TextArea textArea;

    public CategoryEditView() {
        setTitle("Chỉnh Sửa Thể Loại");
        setBounds(100, 100, 462, 486);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblName = new JLabel("Tên Thể Loại:");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblName.setBounds(30, 189, 134, 25);
        getContentPane().add(lblName);

        nameTf = new JTextField();
        nameTf.setBounds(216, 189, 200, 25);
        getContentPane().add(nameTf);

        JLabel lblDescription = new JLabel("Mô Tả:");
        lblDescription.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblDescription.setBounds(30, 224, 100, 25);
        getContentPane().add(lblDescription);

        saveButton = new JButton("Lưu");
        saveButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        saveButton.setBounds(326, 394, 90, 30);
        getContentPane().add(saveButton);
        
        JLabel lblNewLabel = new JLabel("Sửa Thể Loại Sách");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel.setBounds(114, 25, 220, 61);
        getContentPane().add(lblNewLabel);
        
        JTextArea textArea = new JTextArea();
        textArea.setBounds(216, 224, 200, 160);
        getContentPane().add(textArea);
        
        JLabel lblMThLoi = new JLabel("Mã thể loại");
        lblMThLoi.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblMThLoi.setBounds(30, 154, 147, 25);
        getContentPane().add(lblMThLoi);
        
        idTf = new JTextField();
        idTf.setBounds(216, 154, 200, 25);
//        idTf.setText("asd");
        getContentPane().add(idTf);
        
        JButton huyBtn = new JButton("Hủy");
        huyBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        huyBtn.setBounds(216, 394, 90, 30);
        getContentPane().add(huyBtn);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                saveCategory();
            }
        });
    }

    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
    		public void run() {
    			try {
    				CategoryEditView frame = new CategoryEditView();
    				frame.setVisible(true);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	});
    }
    public CategoryEditView(Category categoryToEdit) {
		System.out.println("Cate name:"+categoryToEdit.getDescription());
		CategoryEditView frame = new CategoryEditView();
		frame.setVisible(true);
		frame.idTf.setText(String.valueOf(categoryToEdit.getId()));
		frame.nameTf.setText(categoryToEdit.getName());
		frame.textArea.setText(categoryToEdit.getDescription());
	}

}
