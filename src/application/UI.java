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
		
		System.out.println("1 - Register item");
		System.out.println("2 - Edit item");
		System.out.println("3 - Find item");
		System.out.println("4 - Delete item");
		System.out.println("5 - Show all");
		System.out.println("6 - Exit");
		int n = sc.nextInt();
		
		//printRegisterItem(productDao, departmentDao, sc);
		printEditItem(productDao, departmentDao, sc);
		//printFind();
		//printDelete();
	}
	
	private static void printRegisterItem(ProductDao productDao, DepartmentDao departmentDao, Scanner sc) {
		System.out.println("1 - Register product");
		System.out.println("2 - Register department");
		System.out.println("3 - Return to menu");
		int n = sc.nextInt();
		
		if (n == 1) {
			registerProduct(productDao, sc);			
		}
		else if (n == 2) {
			registerDepartment(departmentDao, sc);
		}
		else if (n == 3) {
			printMenu(sc);
		}
		else {
			throw new DBException("Invalid data");
		}
	}
	
	private static void registerProduct(ProductDao productDao, Scanner sc) {
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
	
	private static void registerDepartment(DepartmentDao departmentDao, Scanner sc) {
		System.out.println("Enter department name:");
		sc.nextLine();
		String name = sc.nextLine();
		
		Department department = new Department(null, name);
		departmentDao.insert(department);
		System.out.println("Inserted! Id = " + department.getId());
	}
	
	private static void printEditItem(ProductDao productDao, DepartmentDao departmentDao, Scanner sc) {
		System.out.println("1 - Edit product");
		System.out.println("2 - Edit department");
		int n = sc.nextInt();
		
		if (n == 1) {
			editProduct(productDao, sc);
		}
		else if (n == 2) {
			editDepartment(departmentDao, sc);
		}
		else {
			throw new DBException("Invalid data");
		}
	}
	
	private static void editProduct(ProductDao productDao, Scanner sc) {
		System.out.print("Enter the Product Id: ");
		int id = sc.nextInt();
		Product product = productDao.findById(id);
		
		System.out.println(product);
		System.out.println("1 - Edit name");
		System.out.println("2 - Edit price");
		System.out.println("3 - Edit quantity");
		System.out.println("4 - Edit departmentId");
		
		int n = sc.nextInt();
		
		switch (n) {
		case 1:
			System.out.print("Name: ");
			sc.nextLine();
			String name = sc.nextLine();
			product.setName(name);
			break;
		case 2:
			System.out.print("Price: ");
			double price = sc.nextDouble();
			product.setPrice(price);
			break;
		case 3:
			System.out.print("Quantity: ");
			int qtd = sc.nextInt();
			product.setQuantity(qtd);
			break;
		case 4:
			System.out.print("DepartmentId: ");
			int departmentId = sc.nextInt();
			Department department = new Department(departmentId, null);
			product.setDepartment(department);
			break;
		default:
			throw new DBException("Invalid data");
		}
		
		productDao.update(product);
		
		System.out.println("Updated product! New data:");
		product = productDao.findById(id);
		System.out.println(product);
	}
	
	private static void editDepartment(DepartmentDao departmentDao, Scanner sc) {
		System.out.print("Enter the Department Id: ");
		int id = sc.nextInt();
		Department department = departmentDao.findById(id);
		System.out.println(department);
		
		System.out.print("Name: ");
		sc.nextLine();
		String name = sc.nextLine();
		department.setName(name);
		departmentDao.update(department);
		System.out.println("Updated department! New data: ");
		System.out.println(department);
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
