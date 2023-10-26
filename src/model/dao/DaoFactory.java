package model.dao;

import db.DB;
import entities.Department;
import entities.Product;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.ProductDaoJDBC;

public class DaoFactory {
	
	public static Dao<Product, Integer> createProductDao() {
		return new ProductDaoJDBC(DB.getConnection());
	}
	
	public static Dao<Department, Integer> createDepartmentDao() {
		return new DepartmentDaoJDBC(DB.getConnection());
	}

}
