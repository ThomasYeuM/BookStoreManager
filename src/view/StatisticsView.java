package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import model.Bill;

public class StatisticsView extends JFrame {
    private static final long serialVersionUID = 1L;

    public StatisticsView(List<Bill> bills) {
        setTitle("Thống kê thu nhập theo tháng");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Tạo dữ liệu biểu đồ
        DefaultCategoryDataset dataset = createDataset(bills);

        // Tạo biểu đồ
        JFreeChart chart = ChartFactory.createBarChart(
                "Thống kê thu nhập theo tháng",
                "Tháng", 
                "Thu nhập (VND)", 
                dataset
        );

        // Thêm biểu đồ vào giao diện
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset createDataset(List<Bill> bills) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Tạo bản đồ để lưu tổng thu nhập theo tháng
        Map<String, Double> incomeByMonth = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"); // Định dạng mới

        for (Bill bill : bills) {
            try {
                String dateStr = bill.getDate(); // Dữ liệu từ hóa đơn
                LocalDate date = LocalDate.parse(dateStr, formatter); // Parse ngày
                String month = date.getMonthValue() + "/" + date.getYear(); // Tháng/Năm

                // Cộng dồn thu nhập theo tháng
                incomeByMonth.put(month,
                    incomeByMonth.getOrDefault(month, 0.0) + bill.getProductExpense());
            } catch (DateTimeParseException e) {
                System.err.println("Lỗi định dạng ngày: " + bill.getDate());
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("Lỗi khác khi xử lý hóa đơn: " + bill);
                e.printStackTrace();
            }
        }

        // Thêm dữ liệu vào dataset
        for (Map.Entry<String, Double> entry : incomeByMonth.entrySet()) {
            dataset.addValue(entry.getValue(), "Thu nhập", entry.getKey());
        }

        return dataset;
    }


}
