package model.dao;

import java.util.List;

public interface Dao<T, K> {
	
	void insert(T obj);
	void update(T obj);
	void deleteById(K id);
	T findById(K id);
	List<T> findAll();	

}
