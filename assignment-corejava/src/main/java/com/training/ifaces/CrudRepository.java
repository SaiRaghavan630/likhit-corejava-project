package com.training.ifaces;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.training.exception.ElementNotFoundException;
import com.training.model.Employee;

public interface CrudRepository<T> {
	
    Collection<T> findAll();
	
	boolean save(T obj);
	


}
