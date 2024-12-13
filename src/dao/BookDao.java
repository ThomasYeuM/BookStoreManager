package dao;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import model.Book;
import util.FileConnector;

public class BookDao implements DAO<Book> {
    String FILE_PATH = "src/db/books.txt"; // Đường dẫn đến file lưu trữ
    private final FileConnector<Book> fileConnector = new FileConnector<Book>();

    @Override
    public List<Book> getAll() throws ClassNotFoundException, IOException {
        return fileConnector.readFromFile(FILE_PATH); // Đọc danh sách sách từ file
    }

    @Override
    public boolean add(Book book) throws ClassNotFoundException, IOException {
        List<Book> books = getAll(); // Lấy tất cả sách hiện có
        for (Book b : books) {
            if (b.getId() == book.getId()) {
                return false;  // Nếu sách đã có trong danh sách thì không thêm
            }
        }
        fileConnector.appendObject(FILE_PATH, book);  // Thêm sách mới vào file
        return true;
    }

    @Override
    public boolean update(Book updateBook) throws ClassNotFoundException, IOException {
        List<Book> books = getAll(); // Lấy tất cả sách hiện có
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == updateBook.getId()) {
                books.set(i, updateBook);  // Cập nhật sách nếu tìm thấy ID trùng
            }
        }
        fileConnector.writeToFile(FILE_PATH, books);  // Ghi danh sách sách đã cập nhật vào file
        return true;
    }

    @Override
    public boolean delete(Book deletedBook) throws ClassNotFoundException, IOException {
        List<Book> books = getAll(); // Lấy tất cả sách hiện có
        books.removeIf(book -> book.getId() == deletedBook.getId());  // Xóa sách theo ID
        fileConnector.writeToFile(FILE_PATH, books);  // Lưu danh sách sách đã xóa vào file
        return true;
    }

    @Override
    public Book get(Predicate<Book> predicate) throws ClassNotFoundException, IOException {
        List<Book> books = fileConnector.readFromFile(FILE_PATH);
        for (Book book : books) {
            if (predicate.test(book)) {
                return book;  // Trả về sách thỏa mãn điều kiện
            }
        }
        return null;  // Nếu không tìm thấy sách, trả về null
    }
}
