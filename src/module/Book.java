package module;

import java.io.Serializable;

public class Book implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int qty;
	private Author author;
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Author getAuthor() {
		return author;
	}
	
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	public Book(int id, String name, int qty, Author author, String description) {
		this.id = id;
		this.name = name;
		this.qty = qty;
		this.author = author;
		this.description = description;
	}
	
	public Book() {
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", qty=" + qty + ", author=" + author + ", description="
				+ description + "]";
	}
	
	
	
}
