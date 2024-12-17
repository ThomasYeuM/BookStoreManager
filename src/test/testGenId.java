package test;

import java.io.IOException;

import util.GenNewId;

public class testGenId {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		
		int newId = GenNewId.getNewBillId();
		System.out.println(newId);
	}

}
