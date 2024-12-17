package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Bill implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String customerName;
	private int adminID;
	private Date date;
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
	public int getAdminID() {
		return adminID;
	}
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
	public Bill(int id, String customerName, int adminID, Date date, HashMap<Book, Integer> books,
			double productExpense) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.adminID = adminID;
		this.date = date;
		Books = books;
		ProductExpense = productExpense;
	}

}
