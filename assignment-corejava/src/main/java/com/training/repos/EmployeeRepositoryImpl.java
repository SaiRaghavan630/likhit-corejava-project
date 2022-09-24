package com.training.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.stream.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.stream.Collectors.toList;
import com.training.exception.ElementNotFoundException;
import com.training.ifaces.CrudRepository;
import com.training.ifaces.EmployeeRepository;
import com.training.model.Employee;
//import com.training.model.Student;
//import com.training.model.Student;
import com.training.utils.ConnectionFactory;

public class EmployeeRepositoryImpl implements EmployeeRepository<Employee> {
	
	private Connection con=ConnectionFactory.getMySqlConnection();
	
	private List<Employee> empList;
	

	public EmployeeRepositoryImpl() {
		super();
		this.empList = new ArrayList<>();
	}

	

	@Override
	public List<Employee> findAll() {
		
    // List<Employee> empList= new ArrayList<>();
		
		try {
			
			String sql= "SELECT * FROM assesment_table ";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//propName (From Student Class)= coloumnName from the lumen_student table
				long phoneNumber = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String address = rs.getString(4);
				LocalDate dateOfBirth = rs.getDate(5).toLocalDate();
				LocalDate weddingDate = rs.getDate(6).toLocalDate();
				String emailAddress = rs.getString(7);
				
				Employee obj = new Employee(firstName, lastName, address,phoneNumber, dateOfBirth,
						weddingDate,emailAddress);
				
				empList.add(obj);
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return empList;
	}

	@Override
	public boolean save(Employee obj) {
		
        String sql= "INSERT INTO assesment_table values(?,?,?,?,?,?,?)";
		
		int rowadded=0;
		
		try {
			PreparedStatement pstmt= con.prepareStatement(sql);
			
			pstmt.setLong(1,obj.getPhoneNumber());
			pstmt.setString(2,obj.getFirstName());
			pstmt.setString(3,obj.getLastName());
			pstmt.setString(4,obj.getAddress());
			java.sql.Date dateOfBirth = java.sql.Date.valueOf(obj.getDateOfBirth());
			pstmt.setDate(5, dateOfBirth);
			java.sql.Date weddingDate = java.sql.Date.valueOf(obj.getWeddingDate());
			pstmt.setDate(6,weddingDate);
			pstmt.setString(7,obj.getEmailAddress());
			rowadded= pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("row is already existing");
		}
		
		return rowadded==1?true:false;
	}

	@Override
	public boolean delete(String name)  {
		
		String query = "DELETE FROM assesment_table WHERE firstName=?";
		
		int rowDeleted = 0;
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1,name);
			rowDeleted = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("no row found");;
		}
		
		return rowDeleted==1?true:false;
	}


	@Override
	public boolean update(String email, long phno) {
		
		String sql = "UPDATE assesment_table set emailAddress = ? where phoneNumber = ?" ;
		
		 int rowUpdated =0; 
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(2,phno);
			pstmt.setString(1,email);
			rowUpdated = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return rowUpdated==1?true:false;
	}
	

}
