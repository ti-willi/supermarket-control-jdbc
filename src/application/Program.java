package application;

import java.util.Locale;
import java.util.Scanner;

import db.DBException;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		try {
			UI.printMenu(sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}

}
