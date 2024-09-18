package module;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Bill implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private int adminID;
	private Date date;
	private HashMap<Book, Integer> Books;
	private double total;
	public Bill() {
	}
	public Bill(String id, String name, int adminID, Date date, HashMap<Book, Integer> books, double total) {
		this.id = id;
		this.name = name;
		this.adminID = adminID;
		this.date = date;
		this.Books = books;
		this.total = total;
		calcTotal();
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAdminID() {
		return adminID;
	}
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("vi", "VN"));
		return sdf.format(date);
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
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	private double calcTotal() {
		return adminID;		
	}
}
