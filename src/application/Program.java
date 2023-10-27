package application;

import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.ProductDao;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		ProductDao productDao = DaoFactory.createProductDao();
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		UI.printMenu(sc);
		
		
	}

}
