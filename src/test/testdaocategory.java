package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.CategoryDAO;
import model.Category;

public class testdaocategory {
	public static void main(String[] args) throws ClassNotFoundException, IOException {
	CategoryDAO cateDao = new CategoryDAO();
	
	//Add them sach vao file
	List<Category> list = new ArrayList<Category>();

	Category cate1 = new Category(40, "cate1", "des1");
	Category cate2 = new Category(30, "cate2", "des2");
	
	list.add(cate1);
	list.add(cate2);
	cateDao.add(cate2);
	
	
	//Them sach vao file
	Category updatedCate = new Category(30, "updateCate", "updateCate");
	cateDao.update(updatedCate);
	
	
	
	
	
	//Xoa sach khoi file
	Category deleteCate = new Category(30, "cate2", "des2");
	cateDao.delete(deleteCate);
	
	//Lay danh sach tat ca cac sach da them
	List<Category> listBooks = cateDao.getAll();
	
	for (Category cate : listBooks) {
        System.out.println("  - ID: " + cate.getId() +"  - Tên: " + cate.getName() + ", Des: " + cate.getDescription());
    }
	
	
	}
	
	
	
	

}
