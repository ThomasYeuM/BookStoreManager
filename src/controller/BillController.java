package controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import dao.BillDAO;
import model.Bill;
import view.BillManagementView;

public class BillController {
	private BillDAO billDAO;
	private BillManagementView bmv;

	public BillController(BillDAO billDAO, BillManagementView bmv) {
		this.billDAO = billDAO;
		this.bmv = bmv;
	}

	public List<Bill> getAllBills() throws ClassNotFoundException, IOException {
		return billDAO.getAll();
	}
}
