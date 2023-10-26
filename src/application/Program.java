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
		Department newDepartment = new Department(null, "Bakery");
		departmentDao.insert(newDepartment);
		System.out.println("Inserted! ID = " + newDepartment.getId());
		
		System.out.println("\n---TEST 2 = product insert---");
		Product newProduct = new Product(null, "Bread", 0.89, 1, newDepartment);
		productDao.insert(newProduct);
		System.out.println("Inserted! ID = " + newProduct.getId());
		
		System.out.println("\n---TEST 3 = department findById---");
		Department department = departmentDao.findById(4);
		System.out.println(department);
		
		System.out.println("\n---TEST 4 = product findById---");
		Product product = productDao.findById(2);
		System.out.println(product);
		
		System.out.println("\n---TEST 5 = department update---");
		department = departmentDao.findById(4);
		department.setName("Frozen");
		departmentDao.update(department);
		System.out.println("Update completed");
		
		System.out.println("\n---TEST 6 = product update---");
		product = productDao.findById(4);
		product.setQuantity(3);
		productDao.update(product);
		System.out.println("Update completed");
		
	}

}
