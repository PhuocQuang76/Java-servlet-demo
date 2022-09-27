package com.example.dao;

import java.util.List;

import com.example.pojos.Employee;

public interface EmployeeDao {
	 public Employee findById(int id) throws EmployeeException;
	 public List<Employee> findAll() throws EmployeeException;
	 public String register (Employee e) throws EmployeeException;   
     public int remove(int id) throws EmployeeException;
     public String update(Employee e) throws EmployeeException;

}
