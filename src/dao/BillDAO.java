package dao;

import java.io.IOException;
import java.util.List;

import model.Bill;
import model.Book;
import util.FileConnector;

public class BillDAO implements DAO<Bill> {
	String FILE_PATH = "db/bills.txt";
	private final FileConnector<Bill> fileConnector = new FileConnector<Bill>();
	@Override
	public List getAll() throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return fileConnector.readFromFile(FILE_PATH);
	}

	@Override
	public boolean add(Bill newBill) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		List<Bill> bills = getAll();
		for(Bill bill: bills) {
			if(bill.getId() == newBill.getId()) {
				return false;
			}
		}
		fileConnector.writeToFile(FILE_PATH, bills);
		return true;
	}

	@Override
	public boolean update(Bill updatedBill) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		List<Bill> bills = getAll();
		
		for (int i = 0; i < bills.size(); i++) {
	        if (bills.get(i).getId() == updatedBill.getId()) {
	        	bills.set(i, updatedBill);
	        }
	    }
		fileConnector.writeToFile(FILE_PATH, bills);
		return true;
	}

	@Override
	public boolean delete(Bill deletedBill) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		List<Bill> bills = getAll();
		bills.removeIf(book -> book.getId() == deletedBill.getId());
		fileConnector.writeToFile(FILE_PATH, bills);
		return true;
	}
	
}
