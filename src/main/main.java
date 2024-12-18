package main;

import java.awt.EventQueue;
import java.io.File;

import util.GetFilePath;
import view.HomepageView;
import view.LoginView;

public class main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
