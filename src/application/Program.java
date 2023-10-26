package application;

import entities.Department;
import entities.Product;
import model.dao.Dao;
import model.dao.DaoFactory;

public class Program {

	public static void main(String[] args) {
		
		Dao<Product, Integer> productDao = DaoFactory.createProductDao();
		Dao<Department, Integer> departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("---TEST 1 = department insert---");
		Department newDepartment = new Department(null, "Drinks");
		departmentDao.insert(newDepartment);
		System.out.println("Inserted! ID = " + newDepartment.getId());
		
		System.out.println("---TEST 2 = department insert---");
		Product newProduct = new Product(null, "Pepsi", 2.49, 1, newDepartment);
		productDao.insert(newProduct);
		System.out.println("Inserted! ID = " + newProduct.getId());
		
	}

}
