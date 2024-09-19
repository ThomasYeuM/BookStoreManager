package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.BookDao;
import model.Author;
import model.Book;

public class testAddBookByDAO {
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		BookDao bookDao = new BookDao();
		
		//Add them sach vao file
		List<Book> list = new ArrayList<Book>();
		
		Author author1 = new Author("testAuthor", "male", "authorTest@gmail.com");
		Book book1 = new Book(10, "test1", 10, 10, author1, "cai gi do");
		Book book2 = new Book(30, "test2", 10, 10, author1, "cai gi do");
		list.add(book1);
		list.add(book2);
		bookDao.add(book2);
		
		//Them sach vao file
		Book updatedBook = new Book(30, "updatedBook", 10, 10, author1, "cai gi do");
		bookDao.update(updatedBook);
		
		//Xoa sach khoi file
		Book deleteBook = new Book(30, "updatedBook", 10, 10, author1, "cai gi do");
		bookDao.delete(deleteBook);
		
		//Lay danh sach tat ca cac sach da them
		List<Book> listBooks = bookDao.getAll();
		for (Book book : listBooks) {
            System.out.println("  - ID: " + book.getId() +"  - Tên: " + book.getName() + ", Tuổi: " + book.getAuthor());
        }
		
		
		
		
		
	}
}
