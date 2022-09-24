package com.training;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


import com.training.exception.ElementNotFoundException;
import com.training.model.Employee;
import com.training.services.EmployeeService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	
    	//CrudRepository<Employee> repo=new EmployeeRepository();
    	EmployeeService service = new EmployeeService();
    	
    	int counter = 1;
    	int choice;
    	LocalDate dateOfBirth=null;
    	LocalDate weddingDate=null;
    	
    	System.out.println("1- add employee details");
    	System.out.println("2- find list of employee in table");
    	System.out.println("3-find list of employee by their firstname");
    	System.out.println("4-find list of employee phone-number and firstname");
    	System.out.println("5-update employee email-address");
    	System.out.println("6-delete employee record using firstname");
    	System.out.println("7-get employee firstname and email using dateofbirth");
    	System.out.println("8-get employee firstname and phonenumber based on wedding anniversary");
    	System.out.println("9-exit ");
    	
    	try(Scanner sc = new Scanner(System.in)){
 
    		choice = sc.nextInt();
    		boolean condition=true;
    	
    		while(condition) {
    			
        
        			
        		switch(choice) {
        		
        		case 1:
        		System.out.println("enter employee firstname");
        		String firstName = sc.next();
        		
        		System.out.println("enter employee last-name");
        		String lastName = sc.next();
        		
        		System.out.println("enter employee address");
        		String address = sc.next();
        		
        		System.out.println("enter employee email-address");
        		String emailAddress = sc.next();
        		
        		System.out.println("enter employee phone-number");
        		long phoneNumber = sc.nextLong();
        		
        		
        		try {
        		System.out.println("enter employee dateofbirth");
        		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
        		dateOfBirth = LocalDate.parse(sc.next(), dateFormat);
        		} catch(Exception e) {
        			
        		   System.out.println("please enter date-of-birth format in d/M/yyyy");
        		   System.exit(0);
        		}
        		
        		try {
        		System.out.println("enter employee wedding-date");
        		DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("d/M/yyyy");
        		weddingDate = LocalDate.parse(sc.next(), dateFormat1);
        		} catch(Exception e) {
        			
         		   System.out.println("please enter wedding-date format in d/M/yyyy");
         		   System.exit(0);
        		}
					
					
					Employee emp = new Employee(firstName, lastName, address, phoneNumber, dateOfBirth,
        				weddingDate, emailAddress);
        		
        		service.save(emp);
        		System.out.println("enter choice");
         		choice = sc.nextInt();
             	break;	
        		
        		
             	case 2:
             		
             	System.out.println("please find the list of employees available in the database");
             	service.findAll().forEach(System.out::println);
             	System.out.println("enter choice");
         		choice = sc.nextInt();
             	break;	
             	
             	case 3:
             		
             	System.out.println("enter first-name of employee");	
             	String empName = sc.next();
					List<Employee> list;
					try {
						service.findByFirstName(empName);
					} catch (ElementNotFoundException e) {
						e.printStackTrace();
					}
					System.out.println("enter choice");
             		choice = sc.nextInt();
             	 break;
             	 
             	case 4:
             		
             		
					service.getFirstNameAndPhoneNumber();
             		System.out.println("enter choice");
             		choice = sc.nextInt();
             		break;
             		
             	case 5:
             		
             		System.out.println("enter new email-address of employee");
             		String newEmpEmail = sc.next();
             		System.out.println("enter phone-number of employee");
             		long empPhno = sc.nextLong();
             		
             		try {
						service.updateByPhno(newEmpEmail, empPhno);
					} catch (ElementNotFoundException e1) {
						e1.printStackTrace();
					}
             		System.out.println("enter choice");
             		choice = sc.nextInt();
             		break;
             		
             	case 6:
             		System.out.println("enter employee first name");
             		String empFirstName = sc.next();
             		try {
						service.deleteByFirstName(empFirstName);
					} catch (ElementNotFoundException e1) {
						e1.printStackTrace();
					}
             		System.out.println("enter choice");
             		choice = sc.nextInt();
             		break;
             	case 7:
             		
             		LocalDate empDateOfBirth;
             		try {
             		System.out.println("enter date");
             		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
            		empDateOfBirth = LocalDate.parse(sc.next(), dateFormat);
            		
             		} catch(Exception e) {
             			throw new RuntimeException("enter date in given format");
             		}
             		
             		
					try {
						service.getFirstNameAndEmail(empDateOfBirth);
						
					} catch (ElementNotFoundException e) {
						
						e.printStackTrace();
					}
					System.out.println("enter choice");
             		choice = sc.nextInt();
            	    break;
             	case 8:
             		LocalDate empWeddingDate;
             		try {
             			
             			System.out.println("enter date to check for employee wedding anniversary");
             			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
             			empWeddingDate = LocalDate.parse(sc.next(), dateFormat);
             		} catch(Exception e) {
             			throw new RuntimeException("enter date in given format");
             		}
             		
             		//Map<String,Long> newMap;
              		try {
              			service.getFirstNameAndPhoneNumber(empWeddingDate);
              			
              		} catch(ElementNotFoundException e) {
              			e.printStackTrace();
              		}
              		System.out.println("enter choice");
             		choice = sc.nextInt();
              	    break;
              		
             	case 9:
             		System.exit(0);
             		break;
             		
        		}
        		
                     			
        		}
        	}
        }
    
}