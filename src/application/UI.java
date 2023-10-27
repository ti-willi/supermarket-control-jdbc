package application;

import java.util.Locale;
import java.util.Scanner;

import db.DBException;
import entities.Department;
import entities.Product;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.ProductDao;

public class UI {
	
	public static void printMenu(Scanner sc) {
		ProductDao productDao = DaoFactory.createProductDao();
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		Locale.setDefault(Locale.US);
		
		System.out.println("1 - Insert item");
		System.out.println("2 - Update item");
		System.out.println("3 - Find item");
		System.out.println("4 - Delete item");
		System.out.println("5 - Show all");
		System.out.println("6 - Exit");
		int n = sc.nextInt();
		
		printInsert(productDao, departmentDao, sc);
		//printUpdate();
		//printFind();
		//printDelete();
	}
	
	private static void printInsert(ProductDao productDao, DepartmentDao departmentDao, Scanner sc) {
		System.out.println("1 - Insert product");
		System.out.println("2 - Insert department");
		System.out.println("3 - Return to menu");
		int n = sc.nextInt();
		
		if (n == 1) {
			insertProduct(productDao, sc);			
		}
		if (n == 2) {
			insertDepartment(departmentDao, sc);
		}
		if (n == 3) {
			printMenu(sc);
		}
		else {
			throw new DBException("Enter a valid number");
		}
	}
	
	private static void insertProduct(ProductDao productDao, Scanner sc) {
		System.out.println("Enter product data:");
		sc.nextLine();
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Price: ");
		double price = sc.nextDouble();
		System.out.print("Quantity: ");
		int qtd = sc.nextInt();
		System.out.print("Department: ");
		int departmentId = sc.nextInt();
		
		Department department = new Department(departmentId, null);
		Product product = new Product(null, name, price, qtd, department);
		
		productDao.insert(product);
		System.out.println("Inserted! Id = " + product.getId());
	}
	
	private static void insertDepartment(DepartmentDao departmentDao, Scanner sc) {
		System.out.println("Enter department name:");
		sc.nextLine();
		String name = sc.nextLine();
		
		Department department = new Department(null, name);
		departmentDao.insert(department);
		System.out.println("Inserted! Id = " + department.getId());
	}
	
	private static void printUpdate(Scanner sc) {
		System.out.println("1 - Update product");
		System.out.println("2 - Update department");
		int n = sc.nextInt();
	}
	
	private static void printFind(Scanner sc) {
		System.out.println("1 - Find product");
		System.out.println("2 - Find department");
		int n = sc.nextInt();
		
		if (n == 1) {
			System.out.println("1 - Find product by Id");
			System.out.println("2 - Find product by department");
		}	
		
	}
	
	private static void printDelete(Scanner sc) {
		System.out.println("1 - Delete product");
		System.out.println("2 - Delete department");
		int n = sc.nextInt();
	}
	
}
