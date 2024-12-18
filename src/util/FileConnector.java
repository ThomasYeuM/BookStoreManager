package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FileConnector<T> {

    // Phương thức ghi dữ liệu vào tệp (một cách ghi đè toàn bộ)
    public void writeToFile(String filePath, List<T> data) throws IOException {
        File fileName = new File(filePath);
        if (!fileName.exists()) {
            fileName.createNewFile();
        }

        // Ghi dữ liệu vào tệp
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data);  // Ghi danh sách dữ liệu vào tệp
        }
    }

    // Phương thức đọc dữ liệu từ tệp (trả về danh sách đối tượng)
    @SuppressWarnings("unchecked")
    public List<T> readFromFile(String filePath) throws IOException, ClassNotFoundException {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (List<T>) ois.readObject();  // Đọc danh sách đối tượng từ tệp
        }
    }

    // Phương thức thêm một đối tượng vào tệp (không ghi đè toàn bộ)
    public void appendObject(String fileName, T newObject) throws IOException, ClassNotFoundException {
        List<T> objects = readFromFile(fileName);
        objects.add(newObject);
        writeToFile(fileName, objects);  // Ghi lại toàn bộ danh sách
    }
    public boolean updateObject(String filePath, T updatedObject, Predicate<T> predicate) throws IOException, ClassNotFoundException {
        List<T> objects = readFromFile(filePath);
        boolean isUpdated = false;

        // Tìm đối tượng trong danh sách và thay thế nếu phù hợp
        for (int i = 0; i < objects.size(); i++) {
            if (predicate.test(objects.get(i))) {
                objects.set(i, updatedObject);  // Thay thế đối tượng cũ
                isUpdated = true;
                break;
            }
        }

        // Ghi lại dữ liệu vào tệp nếu có sự thay đổi
        if (isUpdated) {
            writeToFile(filePath, objects);
        }

        return isUpdated;
    }
}
