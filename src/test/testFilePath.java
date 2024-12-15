package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


import model.Book;


public class testFilePath {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
			String FILE_PATH = "src/db/books.txt";;
			
			//Tao 1 danh sach book
			List<Book> list = new ArrayList<Book>();

			Book book1 = new Book(15, "test1", 10, 10,"Author1", "cai gi do");
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

		
	}

}
