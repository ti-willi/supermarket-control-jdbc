package application;

import java.util.List;
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
		
		Locale.setDefault(Locale.US);
		
		ProductDao productDao = DaoFactory.createProductDao();
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("1 - Register item");
		System.out.println("2 - Edit item");
		System.out.println("3 - Find item");
		System.out.println("4 - Delete item");
		System.out.println("5 - Show all");
		System.out.println("6 - Exit");
		int n = sc.nextInt();
		
		switch (n) {
		case 1:
			printRegisterItem(productDao, departmentDao, sc);
			break;
		case 2: 
			printEditItem(productDao, departmentDao, sc);
			break;
		case 3:
			printFindItem(productDao, departmentDao, sc);
			break;
		case 4:
			printDeleteItem(productDao, departmentDao, sc);
			break;
		case 5:
			showAllItems(productDao, departmentDao, sc);
			break;
		case 6:
			break;
		default:
			throw new DBException("Invalid data");
		}
		
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
		System.out.println("3 - Return to menu");
		int n = sc.nextInt();
		
		if (n == 1) {
			editProduct(productDao, sc);
		}
		else if (n == 2) {
			editDepartment(departmentDao, sc);
		}
		else if (n == 3) {
			printMenu(sc);
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
	
	private static void printFindItem(ProductDao productDao, DepartmentDao departmentDao, Scanner sc) {
		System.out.println("1 - Find product");
		System.out.println("2 - Find department");
		System.out.println("3 - Return to menu");
		int n = sc.nextInt();
		
		if (n == 1) {
			System.out.println("1 - Find product by Id");
			System.out.println("2 - Find product by department");
			n = sc.nextInt();
			
			if (n == 1) {
				findProductById(productDao, sc);
			}
			else if (n == 2) {
				findProductByDepartment(productDao, sc);
			}
			else {
				throw new DBException("Invalid data");
			}
		}
		else if (n == 2) {
			findDepartment(departmentDao, sc);
		}
		else if (n == 3) {
			printMenu(sc);
		}
		else {
			throw new DBException("Invalid data");
		}
		
	}
	
	private static void findProductById(ProductDao productDao, Scanner sc) {
		System.out.print("Enter the Product Id: ");
		int id = sc.nextInt();
		Product product = productDao.findById(id);
		System.out.println(product);
	}
	
	private static void findProductByDepartment(ProductDao productDao, Scanner sc) {
		System.out.print("Enter the Department Id: ");
		int id = sc.nextInt();
		Department department  = new Department(id, null);
		List<Product> list = productDao.findByDepartment(department);
		for (Product obj : list) {
			System.out.println(obj);
		}
	}
	private static void findDepartment(DepartmentDao departmentDao, Scanner sc) {
		System.out.print("Enter the department Id: ");
		int id = sc.nextInt();
		Department department = departmentDao.findById(id);
		System.out.println(department);		
	}
	
	private static void printDeleteItem(ProductDao productDao, DepartmentDao departmentDao, Scanner sc) {
		System.out.println("1 - Delete product");
		System.out.println("2 - Delete department");
		System.out.println("3 - Return to menu");
		int n = sc.nextInt();
		
		if (n == 1) {
			deleteProduct(productDao, sc);
		}
		else if (n == 2) {
			deleteDepartment(departmentDao, sc);
		}
		else if (n == 3) {
			printMenu(sc);
		}
		else {
			throw new DBException("Invalid data");
		}
	}
	
	private static void deleteProduct(ProductDao productDao, Scanner sc) {
		showAllProducts(productDao);
		
		System.out.print("\nEnter the Product Id: ");
		int id = sc.nextInt();
		productDao.deleteById(id);
		System.out.println("Delete completed");
	}
	
	private static void deleteDepartment(DepartmentDao departmentDao, Scanner sc) {
		showAllDepartments(departmentDao);
		
		System.out.print("\nEnter the Department Id: ");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Delete completed");
	}
	
	private static void showAllItems(ProductDao productDao, DepartmentDao departmentDao, Scanner sc) {
		System.out.println("Products:\n");
		showAllProducts(productDao);
		
		System.out.println("\nDepartments:\n");
		showAllDepartments(departmentDao);
		
		System.out.println("\n1 - Return to menu");
		int n = sc.nextInt();
		
		if (n == 1) {
			printMenu(sc);
		}
		else {
			throw new DBException("Invalid data");
		}
	}
	
	private static void showAllProducts(ProductDao productDao) {
		List<Product> products = productDao.findAll();
		for (Product p : products) {
			System.out.println(p);
		}
	}
	
	private static void showAllDepartments(DepartmentDao departmentDao) {
		List<Department> departments = departmentDao.findAll();
		for (Department dep : departments) {
			System.out.println(dep);
		}
	}
	
}
