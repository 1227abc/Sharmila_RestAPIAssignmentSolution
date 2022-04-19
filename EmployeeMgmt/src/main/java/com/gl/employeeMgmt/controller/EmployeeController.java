package com.gl.employeeMgmt.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.employeeMgmt.entity.Employee;
import com.gl.employeeMgmt.entity.Role;
import com.gl.employeeMgmt.entity.User;
import com.gl.employeeMgmt.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {

		this.employeeService = employeeService;
	}

	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return employeeService.saveUser(user);
	}

	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role) {
		return employeeService.saveRole(role);
	}

	@GetMapping("/employees")
	public List<Employee> findAll() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName = auth.getAuthorities();
		System.out.println(currentPrincipalName);
		return employeeService.findAll();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee findById(@PathVariable int employeeId) {
		Employee emp = employeeService.findById(employeeId);

		if (emp == null) {
			throw new RuntimeException("Employee ID not found" + employeeId);
		}

		return emp;
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee emp) {
		employeeService.save(emp);

		return emp;
	}

	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee emp) {
		employeeService.save(emp);

		return emp;
	}

	@DeleteMapping("/employees/{id}")
	public String deleteEmp(@PathVariable("id") int id) {
		Employee employee = employeeService.findById(id);

		if (employee == null) {
			throw new RuntimeException("The employee " + id + "is not present");
		}

		else {
			employeeService.delete(id);
		}

		return "Deleted employee id -  " + id;

	}

	@GetMapping("/employees/search/{firstName}")
	public List<Employee> searchByFirstName(@PathVariable("firstName") String firstName) {
		List<Employee> employees = employeeService.findByFirstName(firstName);

		return employees;
	}

	@GetMapping("/employees/sort")
	public List<Employee> sortByFirstName(@RequestParam("order") String order) {
		List<Employee> employees = employeeService.sortByFirstName(order);

		return employees;
	}

}
