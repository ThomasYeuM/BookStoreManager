package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Bill implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String customerName;
	private String adminName;
	private String date;
	private HashMap<Book, Integer> Books;
	private double ProductExpense;
	public Bill() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAdminID() {
		return adminName;
	}
	public void setAdminID(String adminName) {
		this.adminName = adminName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public HashMap<Book, Integer> getBooks() {
		return Books;
	}
	public void setBooks(HashMap<Book, Integer> books) {
		Books = books;
	}
	public double getProductExpense() {
		return ProductExpense;
	}
	public void setProductExpense(double productExpense) {
		ProductExpense = productExpense;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Bill(int id, String customerName, String adminName, String date, HashMap<Book, Integer> books,
			double productExpense) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.adminName = adminName;
		this.date = date;
		Books = books;
		this.ProductExpense = 0;
		calcTotal();
	}
	public void calcTotal() {
		for (Book book : Books.keySet()) {
			ProductExpense += book.getPrice() * Books.get(book);
		}
	}
}
