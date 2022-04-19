package com.gl.employeeMgmt.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gl.employeeMgmt.dao.EmployeeRepository;
import com.gl.employeeMgmt.dao.RoleRepository;
import com.gl.employeeMgmt.dao.UserRepository;
import com.gl.employeeMgmt.entity.Employee;
import com.gl.employeeMgmt.entity.Role;
import com.gl.employeeMgmt.entity.User;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	BCryptPasswordEncoder enc;

	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		this.employeeRepository = theEmployeeRepository;
	}

	@Override
	public List<Employee> findAll() {

		List<Employee> employee = employeeRepository.findAll();

		return employee;
	}

	@Override
	public Employee findById(int id) {

		Optional<Employee> result = employeeRepository.findById(id);

		Employee employee = null;

		if (result.isPresent()) {
			employee = result.get();
		} else {

			throw new RuntimeException("The employee " + id + "is not present");
		}

		return employee;
	}

	@Override
	public List<Employee> findByFirstName(String firstName) {

		return employeeRepository.findByFirstNameContainsAllIgnoreCase(firstName);

	}

	@Override
	public void save(Employee employee) {

		employeeRepository.save(employee);
	}

	@Override
	public void delete(int id) {

		Employee employee = employeeRepository.getById(id);

		employeeRepository.delete(employee);
	}

	@Override
	public User saveUser(User user) {

		user.setPassword(enc.encode(user.getPassword()));
		return userRepository.save(user);

	}

	@Override
	public Role saveRole(Role role) {

		roleRepository.save(role);

		return role;
	}

	@Override
	public List<Employee> sortByFirstName(String order) {

		if (order.equalsIgnoreCase("Asc")) {

			return employeeRepository.findAllByOrderByFirstNameAsc();

		}

		else
			return employeeRepository.findAllByOrderByFirstNameDesc();

	}
}
