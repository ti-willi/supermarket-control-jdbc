package application;

import java.util.InputMismatchException;
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
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";	
	
	public static void printMenu(Scanner sc) {
		
		Locale.setDefault(Locale.US);
		
		ProductDao productDao = DaoFactory.createProductDao();
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("1 - Register item");
		System.out.println("2 - Add item");
		System.out.println("3 - Edit item");
		System.out.println("4 - Find item");
		System.out.println("5 - Delete item");
		System.out.println("6 - Show all items");
		System.out.println("7 - Exit");
		
		try {	
			int n = sc.nextInt();
			
			switch (n) {
			case 1:
				printRegisterItem(productDao, departmentDao, sc);
				break;
			case 2:
				printAddItem(productDao, sc);
				break;
			case 3: 
				printEditItem(productDao, departmentDao, sc);
				break;
			case 4:
				printFindItem(productDao, departmentDao, sc);
				break;
			case 5:
				printDeleteItem(productDao, departmentDao, sc);
				break;
			case 6:
				printShowAllItems(productDao, departmentDao, sc);
				break;
			case 7:
				break;
			default:
				throw new DBException("\nInvalid data, try again\n");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			printMenu(sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			printMenu(sc);
		}
	
	}
	
	private static void printRegisterItem(ProductDao productDao, DepartmentDao departmentDao, Scanner sc) {
		System.out.println("1 - Register product");
		System.out.println("2 - Register department");
		System.out.println("3 - Return to menu");
		
		try {
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
				throw new DBException("\nInvalid data, try again\n");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			printRegisterItem(productDao, departmentDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			printRegisterItem(productDao, departmentDao, sc);
		}
		
	}
	
	private static void printAddItem(ProductDao productDao, Scanner sc) {
		addItem(productDao, sc);
		printSubmenuAddItem(sc);
	}
	
	private static void printEditItem(ProductDao productDao, DepartmentDao departmentDao, Scanner sc) {
		System.out.println("1 - Edit product");
		System.out.println("2 - Edit department");
		System.out.println("3 - Return to menu");
		
		try {
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
				throw new DBException("Invalid data, try again");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again");
			sc.nextLine();
			printEditItem(productDao, departmentDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			printEditItem(productDao, departmentDao, sc);
		}
	
	}
	
	private static void printFindItem(ProductDao productDao, DepartmentDao departmentDao, Scanner sc) {
		System.out.println("1 - Find product");
		System.out.println("2 - Find department");
		System.out.println("3 - Return to menu");
		
		try {
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
					throw new DBException("\nInvalid data, try again\n");
				}
			}
			else if (n == 2) {
				findDepartment(departmentDao, sc);
			}
			else if (n == 3) {
				printMenu(sc);
			}
			else {
				throw new DBException("\nInvalid data, try again\n");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			printFindItem(productDao, departmentDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			printFindItem(productDao, departmentDao, sc);
		}
		
	}
	
	private static void printDeleteItem(ProductDao productDao, DepartmentDao departmentDao, Scanner sc) {
		System.out.println("1 - Delete product");
		System.out.println("2 - Delete department");
		System.out.println("3 - Return to menu");
		
		try {
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
				throw new DBException("\nInvalid data, try again\n");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			printDeleteItem(productDao, departmentDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			printDeleteItem(productDao, departmentDao, sc);
		}
	}
	
	private static void printShowAllItems(ProductDao productDao, DepartmentDao departmentDao, Scanner sc) {
		System.out.println("Products:\n");
		showAllProducts(productDao);
		
		System.out.println("\nDepartments:\n");
		showAllDepartments(departmentDao);
		
		printSubmenuShowAllItems(sc);
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
	
	private static void registerProduct(ProductDao productDao, Scanner sc) {
		try {
			sc.nextLine();
			System.out.println("Enter product data:");
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
			
			printSubmenuRegisterProduct(productDao, sc);
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			registerProduct(productDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			registerProduct(productDao, sc);
		}
		
	}
	
	private static void registerDepartment(DepartmentDao departmentDao, Scanner sc) {
		System.out.println("Enter department name:");
		sc.nextLine();
		String name = sc.nextLine();
		
		Department department = new Department(null, name);
		departmentDao.insert(department);
		System.out.println("Inserted! Id = " + department.getId());
		
		printSubmenuRegisterDepartment(departmentDao, sc);
	}
	
	private static void addItem(ProductDao productDao, Scanner sc) {
		System.out.print("Enter the Id product: ");
		try {
			int id = sc.nextInt();
			
			Product product = productDao.findById(id);
			
			int qtd = product.getQuantity() + 1;
			product.setQuantity(qtd);
			
			productDao.update(product);
			System.out.println("Product successfully added!");
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			addItem(productDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			sc.nextLine();
			addItem(productDao, sc);
		}
		
	}
	
	private static void editProduct(ProductDao productDao, Scanner sc) {		
		System.out.print("\nEnter the Product Id: ");
		try {
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
				sc.nextLine();
				product.setPrice(price);
				break;
			case 3:
				System.out.print("Quantity: ");
				int qtd = sc.nextInt();
				sc.nextLine();
				product.setQuantity(qtd);
				break;
			case 4:
				System.out.print("DepartmentId: ");
				int departmentId = sc.nextInt();
				sc.nextLine();
				Department department = new Department(departmentId, null);
				product.setDepartment(department);
				break;
			default:
				throw new DBException("Invalid data, try again");
			}
		
			productDao.update(product);
			
			System.out.println("Updated product! New data:");
			product = productDao.findById(id);
			System.out.println(product);
			
			printSubmenuEditProduct(productDao, sc);
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			editProduct(productDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			editProduct(productDao, sc);
		}
	}
	
	private static void editDepartment(DepartmentDao departmentDao, Scanner sc) {		
		System.out.print("\nEnter the Department Id: ");
		try {
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
			
			printSubmenuEditDepartment(departmentDao, sc);			
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again");
			sc.nextLine();
			editDepartment(departmentDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			editDepartment(departmentDao, sc);
		}
	}
	
	private static void findProductById(ProductDao productDao, Scanner sc) {
		System.out.print("Enter the Product Id: ");
		try {
			int id = sc.nextInt();
			sc.nextLine();
			Product product = productDao.findById(id);
			System.out.println(product);
			
			printSubmenuFindProductById(productDao, sc);
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			findProductById(productDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			findProductById(productDao, sc);
		}
	}
	
	private static void findProductByDepartment(ProductDao productDao, Scanner sc) {
		System.out.print("Enter the Department Id: ");
		try {
			int id = sc.nextInt();
			sc.nextLine();
			Department department  = new Department(id, null);
			List<Product> list = productDao.findByDepartment(department);
			
			for (Product obj : list) {
				System.out.println(obj);
			}
			
			printSubmenuFindProductByDepartment(productDao, sc);
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			findProductByDepartment(productDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			findProductByDepartment(productDao, sc);
		}
	}
	
	private static void findDepartment(DepartmentDao departmentDao, Scanner sc) {
		System.out.print("Enter the department Id: ");
		try {
			int id = sc.nextInt();
			sc.nextLine();
			Department department = departmentDao.findById(id);
			
			if (department != null) {
				System.out.println(department);	
				
				printSubmenuFindDepartment(departmentDao, sc);
			}
			else {
				throw new DBException("\nId not found, try again\n");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			findDepartment(departmentDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			findDepartment(departmentDao, sc);
		}
	}
	
	private static void deleteProduct(ProductDao productDao, Scanner sc) {		
		System.out.print("\nEnter the Product Id: ");
		try {
			int id = sc.nextInt();
			sc.nextLine();
			productDao.deleteById(id);
			
			System.out.println("Delete completed");
			
			printSubmenuDeleteProduct(productDao, sc);
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			deleteProduct(productDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			deleteProduct(productDao, sc);
		}
	}
	
	private static void deleteDepartment(DepartmentDao departmentDao, Scanner sc) {		
		System.out.print("\nEnter the Department Id: ");
		try {
			int id = sc.nextInt();
			sc.nextLine();
			departmentDao.deleteById(id);
			System.out.println("Delete completed");
			
			printSubmenuDeleteDepartment(departmentDao, sc);
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			deleteDepartment(departmentDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			deleteDepartment(departmentDao, sc);
		}
	}
	
	private static void printSubmenuRegisterProduct(ProductDao productDao, Scanner sc) {
		System.out.println("\n1 - Resgiter another product");
		System.out.println("2 - Return to menu");
		System.out.println("3 - End");
		try {
			int n = sc.nextInt();
			
			switch (n) {
			case 1:
				registerProduct(productDao, sc);
				break;
			case 2:
				printMenu(sc);
				break;
			case 3:
				break;
			default:
				throw new DBException("Invalid data, try again");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			printSubmenuRegisterProduct(productDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			printSubmenuRegisterProduct(productDao, sc);
		}
		
	}
	
	private static void printSubmenuRegisterDepartment(DepartmentDao departmentDao, Scanner sc) {
		System.out.println("\n1 - Resgiter another department");
		System.out.println("2 - Return to menu");
		System.out.println("3 - End");
		try {
			int n = sc.nextInt();
			
			switch (n) {
			case 1:
				registerDepartment(departmentDao, sc);
				break;
			case 2:
				printMenu(sc);
				break;
			case 3:
				break;
			default:
				throw new DBException("\nInvalid data, try again\n");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			printSubmenuRegisterDepartment(departmentDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			printSubmenuRegisterDepartment(departmentDao, sc);
		}
	}
	
	private static void printSubmenuAddItem(Scanner sc) {
		System.out.println("\n1 - Return to menu");
		System.out.println("2 - End");
		try {
			int n = sc.nextInt();
			
			switch (n) {
			case 1:
				printMenu(sc);
				break;
			case 2:
				break;
			default:
				throw new DBException("Invalid data, try again");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			printSubmenuAddItem(sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			printSubmenuAddItem(sc);
		}
	}
	
	private static void printSubmenuEditProduct(ProductDao productDao, Scanner sc) {
		System.out.println("\n1 - Edit another product");
		System.out.println("2 - Return to menu");
		System.out.println("3 - End");
		try {
			int n = sc.nextInt();
			
			switch (n) {
			case 1:
				editProduct(productDao, sc);
				break;
			
			case 2:
				printMenu(sc);
				break;
			case 3:
				break;
			default:
				throw new DBException("Invalid data, try again");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			printSubmenuEditProduct(productDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			printSubmenuEditProduct(productDao, sc);
		}
	}
	
	private static void printSubmenuEditDepartment(DepartmentDao departmentDao, Scanner sc) {
		System.out.println("\n1 - Edit another department");
		System.out.println("2 - Return to menu");
		System.out.println("3 - End");
		try {
			int n = sc.nextInt();
			
			switch (n) {
			case 1:
				editDepartment(departmentDao, sc);
				break;
			
			case 2:
				printMenu(sc);
				break;
			case 3:
				break;
			default:
				throw new DBException("Invalid data, try again");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			printSubmenuEditDepartment(departmentDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			printSubmenuEditDepartment(departmentDao, sc);
		}
	}
	
	private static void printSubmenuFindProductById(ProductDao productDao, Scanner sc) {
		System.out.println("\n1 - Find another product");
		System.out.println("2 - Return to menu");
		System.out.println("3 - End");
		try {
			int n = sc.nextInt();
			
			switch (n) {
			case 1:
				findProductById(productDao, sc);
				break;
			
			case 2:
				printMenu(sc);
				break;
			case 3:
				break;
			default:
				throw new DBException("Invalid data, try again");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			printSubmenuFindProductById(productDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			printSubmenuFindProductById(productDao, sc);
		}
	}
	
	private static void printSubmenuFindProductByDepartment(ProductDao productDao, Scanner sc) {
		System.out.println("\n1 - Find another product");
		System.out.println("2 - Return to menu");
		System.out.println("3 - End");
		try {
			int n = sc.nextInt();
			
			switch (n) {
			case 1:
				findProductByDepartment(productDao, sc);
				break;
			
			case 2:
				printMenu(sc);
				break;
			case 3:
				break;
			default:
				throw new DBException("Invalid data, try again");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			printSubmenuFindProductByDepartment(productDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			printSubmenuFindProductByDepartment(productDao, sc);
		}
	}
	
	private static void printSubmenuFindDepartment (DepartmentDao departmentDao, Scanner sc) {
		System.out.println("\n1 - Find another department");
		System.out.println("2 - Return to menu");
		System.out.println("3 - End");
		try {
			int n = sc.nextInt();
			
			switch (n) {
			case 1:
				findDepartment(departmentDao, sc);
				break;
			
			case 2:
				printMenu(sc);
				break;
			case 3:
				break;
			default:
				throw new DBException("Invalid data, try again");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			printSubmenuFindDepartment(departmentDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			printSubmenuFindDepartment(departmentDao, sc);
		}
	}
	
	private static void printSubmenuDeleteProduct(ProductDao productDao, Scanner sc) {
		System.out.println("\n1 - Delete another product");
		System.out.println("2 - Return to menu");
		System.out.println("3 - End");
		try {
			int n = sc.nextInt();
			
			switch (n) {
			case 1:
				deleteProduct(productDao, sc);
				break;
			
			case 2:
				printMenu(sc);
				break;
			case 3:
				break;
			default:
				throw new DBException("Invalid data, try again");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			printSubmenuDeleteProduct(productDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			printSubmenuDeleteProduct(productDao, sc);
		}
	}
	
	private static void printSubmenuDeleteDepartment(DepartmentDao departmentDao, Scanner sc) {
		System.out.println("\n1 - Delete another department");
		System.out.println("2 - Return to menu");
		System.out.println("3 - End");
		try {
			int n = sc.nextInt();
			
			switch (n) {
			case 1:
				deleteDepartment(departmentDao, sc);
				break;
			
			case 2:
				printMenu(sc);
				break;
			case 3:
				break;
			default:
				throw new DBException("Invalid data, try again");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			printSubmenuDeleteDepartment(departmentDao, sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			printSubmenuDeleteDepartment(departmentDao, sc);
		}
	}
	
	private static void printSubmenuShowAllItems(Scanner sc) {
		System.out.println("1 - Return to menu");
		System.out.println("2 - End");
		try {
			int n = sc.nextInt();
			
			switch (n) {
			case 1:
				printMenu(sc);
				break;
			case 2:
				break;
			default:
				throw new DBException("Invalid data, try again");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("\nInvalid data, try again\n");
			sc.nextLine();
			printSubmenuShowAllItems(sc);
		}
		catch (DBException e) {
			System.out.println(e.getMessage());
			printSubmenuShowAllItems(sc);
		}
	}
	
}
