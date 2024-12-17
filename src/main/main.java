package main;

import java.awt.EventQueue;
import java.io.File;

import test.testUser;
import util.GetFilePath;
import view.HomepageView;
import view.LoginView;

public class main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					testUser TestUser = new testUser();
				
//					LoginView frame = new LoginView();
					HomepageView frame = new HomepageView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
