package model;

import java.io.Serializable;

public class Book implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int qty;
	private double price;
	private String author;
	private String description;
	private Category category;
	
	public Book(int id, String name, int qty, double price, String author, String description, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.qty = qty;
		this.price = price;
		this.author = author;
		this.description = description;
		this.category = category;
	}


	
	public Book(String name, double price) {
		super();
		this.name = name;

		this.price = price;
	}



	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


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


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", qty=" + qty + ", price=" + price + ", author=" + author
				+ ", description=" + description + ", category=" + category + "]";
	}



	
	
	
}
