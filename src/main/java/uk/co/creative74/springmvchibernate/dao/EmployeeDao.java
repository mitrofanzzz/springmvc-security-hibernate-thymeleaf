package uk.co.creative74.springmvchibernate.dao;

import java.util.List;

import uk.co.creative74.springmvchibernate.model.Employee;


public interface EmployeeDao {

	Employee findById(int id);

	void saveEmployee(Employee employee);
	
	void deleteEmployeeBySsn(String ssn);
	
	List<Employee> findAllEmployees();

	Employee findEmployeeBySsn(String ssn);

}
