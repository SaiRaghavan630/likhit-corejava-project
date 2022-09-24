package com.training.model;

import java.time.LocalDate;

public class Employee {
	
	private String firstName;
	private String lastName;
	private String address;
	private long phoneNumber;
	private LocalDate dateOfBirth;
	private LocalDate weddingDate;
	private String emailAddress;
	
	public Employee(String firstName, String lastName, String address, long phoneNumber, LocalDate dateOfBirth,
			LocalDate weddingDate, String emailAddress) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.weddingDate = weddingDate;
		this.emailAddress = emailAddress;
	}
	
	public Employee(String firstName, long phoneNumber) {
		super();
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public LocalDate getWeddingDate() {
		return weddingDate;
	}
	
	public void setWeddingDate(LocalDate weddingDate) {
		this.weddingDate = weddingDate;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", phoneNumber="
				+ phoneNumber + ", dateOfBirth=" + dateOfBirth + ", weddingDate=" + weddingDate + ", emailAddress="
				+ emailAddress + "]";
	}
	
	

}
