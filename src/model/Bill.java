package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Bill implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private Customer customer;
	private int adminID;
	private Date date;
	private HashMap<Book, Integer> Books;
	private double ProductExpense;
	public Bill() {
	}
	
	public Bill(String id, String name, Customer customer, int adminID, Date date, HashMap<Book, Integer> books, double productExpense) {
		this.id = id;
		this.name = name;
		this.customer = customer;
		this.adminID = adminID;
		this.date = date;
		Books = books;
		ProductExpense = productExpense;
		calcTotal();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Customer getCustomer() {
		return customer;
	}

	public int getAdminID() {
		return adminID;
	}

	public Date getDate() {
		return date;
	}

	public HashMap<Book, Integer> getBooks() {
		return Books;
	}

	public double getProductExpense() {
		return this.ProductExpense;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setBooks(HashMap<Book, Integer> books) {
		Books = books;
	}

	public void setProductExpense(double productExpense) {
		for (Book book : Books.keySet()) {
			productExpense += book.getPrice() * Books.get(book);
		}
		this.ProductExpense = productExpense;
	}
	
	public double calcTotal() {
		String type = customer.getMemberType();
		double productDiscount = DiscountRate.getProductDiscountRate(type);
		return ( (this.ProductExpense - this.ProductExpense*productDiscount));
		
	}
}
