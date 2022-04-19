package com.gl.employeeMgmt.service;

import java.util.List;
import com.gl.employeeMgmt.entity.Employee;
import com.gl.employeeMgmt.entity.Role;
import com.gl.employeeMgmt.entity.User;

public interface EmployeeService {

	public Role saveRole(Role role);

	public User saveUser(User user);

	public void save(Employee employee);

	public List<Employee> findAll();

	public Employee findById(int id);

	public void delete(int id);

	public List<Employee> findByFirstName(String firstName);

	public List<Employee> sortByFirstName(String order);

}
