package com.login.EmployeeManagementAjax.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.login.EmployeeManagementAjax.model.Employees;

@Repository
public interface EmployeesDao  extends JpaRepository<Employees, Integer>{

	@Query(name = "getEmployeeById")
	public Employees getEmployeeByEmpId(String emp_id);
	
	@Query(name = "loginN",nativeQuery = true)
	Employees loginValidate(String emp_id,String password);
	
	@Query(name = "login")
	Employees loginValidates(String emp_id,String password);
	
}
