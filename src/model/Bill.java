package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Bill implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private Customer customer;
	private int adminID;
	private Date date;
	private HashMap<Book, Integer> Books;
	private double ProductExpense;
	public Bill() {
	}
	
	public Bill(String id, Customer customer, int adminID, Date date, HashMap<Book, Integer> books) {
		this.id = id;
		this.customer = customer;
		this.adminID = adminID;
		this.date = date;
		Books = books;
	}

	public String getId() {
		return id;
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
		
			
		this.ProductExpense = productExpense;
	}
	
	public double calcTotal() {
		double productExpense = 0;
		for (Book book : Books.keySet()) {
			productExpense += book.getPrice() * Books.get(book);
		}
		String type = customer.getMemberType();
		double productDiscount = DiscountRate.getProductDiscountRate(type);
		return ( (productExpense - productExpense*productDiscount));
		
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", customer=" + customer + ", adminID=" + adminID + ", date=" + date + ", Books="
				+ Books + ", Price =" + calcTotal() + "]";
	}

	
}
