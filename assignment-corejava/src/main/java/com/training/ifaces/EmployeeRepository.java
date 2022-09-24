package com.training.ifaces;

public interface EmployeeRepository<T> extends CrudRepository<T> {
	
    boolean delete(String name);
	
	boolean update(String email, long phno);

}
