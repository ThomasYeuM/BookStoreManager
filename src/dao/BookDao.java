package dao;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import model.Book;
import util.FileConnector;

public class BookDao implements DAO<Book> {
	String FILE_PATH = "src/db/books.txt";
	private final FileConnector<Book> fileConnector = new FileConnector<Book>();
	
	
	@Override
	public List<Book> getAll() throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return fileConnector.readFromFile(FILE_PATH);
	}

	@Override
	public boolean add(Book book) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		List<Book> books = getAll();
		for(Book b : books) {
			if(b.getId() == book.getId()) {
				return false;
			}
		}
		fileConnector.appendObject(FILE_PATH, book);
		return true;
	}

	@Override
	public boolean update(Book updateBook) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		List<Book> books = getAll();
		for (int i = 0; i < books.size(); i++) {
	        if (books.get(i).getId() == updateBook.getId()) {
	        	books.set(i, updateBook);
	        }
	    }
		fileConnector.writeToFile(FILE_PATH, books);
		return true;
	}

	@Override
	public boolean delete(Book deletedBook) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		List<Book> books = getAll();
		books.removeIf(book -> book.getId() == deletedBook.getId());
		fileConnector.writeToFile(FILE_PATH, books);
		return true;
	}

	@Override
	public Book get(Predicate<Book> predicate) throws ClassNotFoundException, IOException {
		List<Book> books = fileConnector.readFromFile(FILE_PATH);
		for (Book book : books) {
			if (predicate.test(book)) {
				return book;
			}
		}
		return null;
	}



}
