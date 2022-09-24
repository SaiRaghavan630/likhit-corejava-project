package com.training.services;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.training.exception.ElementNotFoundException;
import com.training.model.Employee;
import com.training.repos.EmployeeRepositoryImpl;

import org.apache.logging.log4j.*;

public class EmployeeService {
	
	private List<Employee> empList;
	private EmployeeRepositoryImpl dao;
	private static final Logger logger=LogManager.getRootLogger();
	//EmployeeRepository dao = new EmployeeRepository();
	
	public EmployeeService() {
		super();
		this.dao = new EmployeeRepositoryImpl();
		
		this.empList = dao.findAll();
		logger.info("service class no arg constructor called");
		
	}

	public void save(Employee obj) {
	
	boolean result = dao.save(obj);	
	//empList = null;
	//empList=dao.findAll();
	
	if(result) {
		
		logger.info("is record added:" +result);
		//return result;
	}
	else {
		
		logger.error("is record added:" + result );
		logger.info("save method:enter valid details and details should not be null");
	}
	  
	}
	
	public List<Employee> findAll(){
		
		//empList = dao.findAll();
		
		
		return this.empList;
		
	}
	
	public void findByFirstName(String name) throws ElementNotFoundException {
		
		List<Employee> list = this.empList.stream().filter(e -> e.getFirstName().equals(name)).collect(toList()); 
		
		if(list.size() == 0) {
			
			logger.error("no matching record,enter correct firstname");
			throw new ElementNotFoundException("no matching employee record found with given firstname");
			
		}
		
		else {
			list.forEach(e->logger.info(e));
			
		}
		
		
		//if(list.le)
	}
	
	public void getFirstNameAndPhoneNumber (){
		
				
		 List<String>	rtnList = new ArrayList<>(); 
			
			for(Employee eachEmp:empList) {
				
				String empFirstName = eachEmp.getFirstName();
				long empPhoneNumber = eachEmp.getPhoneNumber();
				rtnList.add("Employee First Name:" + empFirstName + " " + "Employee phone number:" +empPhoneNumber);
			
		  }
	
		
		rtnList.forEach(System.out::println);	
	}
	
	public void updateByPhno(String email, long phno) throws ElementNotFoundException {
	
		boolean result = this.dao.update(email, phno);
		empList = null;
		empList=dao.findAll();
		
		if(!result) {
			throw new ElementNotFoundException("no phonenumber in database matching with given phonenumber");
			
		}
		
		else {
			
			logger.info("is employee data updated:" + result);
		}
				
	}
	
	public boolean deleteByFirstName(String name) throws ElementNotFoundException{
		
		boolean result = dao.delete(name);
		empList=null;
		empList=dao.findAll();
		
		if(!result) {
			throw new ElementNotFoundException("no employee with given firstname to delete record");
		}
		
		logger.info("is employee record deleted:" + result);
		return result;
		
		
		
	}	
	
	public void getFirstNameAndEmail (LocalDate date) throws ElementNotFoundException {
		
	List<Employee> list = empList.stream().filter(e->e.getDateOfBirth().getYear() <= date.getYear()).filter(e->e.getDateOfBirth().getMonth()==date.getMonth()).filter(e->e.getDateOfBirth().getDayOfMonth()==date.getDayOfMonth()).collect(toList());
	  
	List<String> rtnList = new ArrayList<>();
	
	if(list.size()!=0) {
		
		for(Employee eachEmp:list) {
			
			String empFirstName = eachEmp.getFirstName();
			String empEmail = eachEmp.getEmailAddress();
			rtnList.add("employee firstname:" + empFirstName + " " + "employee email:" + empEmail);
		}
	}
	else {
		throw new ElementNotFoundException("No matching employee records found with given data");
	}
	rtnList.forEach(System.out::println);
	}
	
	public void getFirstNameAndPhoneNumber(LocalDate date) throws ElementNotFoundException {
		
		List<Employee> list = empList.stream().filter(e->e.getWeddingDate().getYear() < date.getYear()).filter(e->e.getWeddingDate().getMonth()==date.getMonth()).
		filter(e->e.getWeddingDate().getDayOfMonth()==date.getDayOfMonth()).collect(toList());

		List<String> rtnList = new ArrayList<>();

		if(list.size()!=0) {
              
                 for(Employee eachEmp:list) {
                	 
                	long empPhoneNumber = eachEmp.getPhoneNumber();
                	String empFirstName = eachEmp.getFirstName();
                	
                	//Employee obj = new Employee(empFirstName,empPhoneNumber);
			        rtnList.add("Employee First Name:" + empFirstName + " " + "Employee phone number:" +empPhoneNumber );
			   }
		} 
		else {
			throw new ElementNotFoundException("No matching employee records found with given data please enter valid data");
		}
		  rtnList.forEach(System.out::println);
		
	}
}
