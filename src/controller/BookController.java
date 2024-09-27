package controller;

import java.io.IOException;
import java.util.List;

import dao.BookDao;

import model.Book;


public class BookController {
	private BookDao bookDao;
	
	public BookController(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	public List<Book> getAllBooks() throws ClassNotFoundException, IOException {
		return bookDao.getAll();
	}
}
