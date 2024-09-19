package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Author;
import model.Book;


public class testFilePath {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
			String FILE_PATH = "db/books.txt";
			
			//Tao 1 danh sach book
			List<Book> list = new ArrayList<Book>();
			Author author1 = new Author("testAuthor", "male", "authorTest@gmail.com");
			Book book1 = new Book(15, "test1", 10, 10, author1, "cai gi do");
			list.add(book1);
			
			FileOutputStream fos = new FileOutputStream(FILE_PATH);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(list); 
			
			oos.close();
			fos.close();
			
			System.out.println("Đã ghi đối tượng vào file thành công!");
			
			//Doc file
			FileInputStream fis = new FileInputStream(FILE_PATH);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			
			List<Book> bookListRead = (List<Book>) ois.readObject();
			System.out.println(bookListRead);
			for (Book book : bookListRead) {
                System.out.println("  - Tên: " + book.getName() + ", Tuổi: " + book.getAuthor());
            }
			
	
		
	}

}
