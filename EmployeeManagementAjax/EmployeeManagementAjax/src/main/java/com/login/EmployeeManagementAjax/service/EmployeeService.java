package com.login.EmployeeManagementAjax.service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.EmployeeManagementAjax.DAO.EmployeesDao;
import com.login.EmployeeManagementAjax.model.Employees;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeesDao dao;
	
	public boolean saveOrUpdate(Employees employees) {
		if(employees.getId()!=null) {
			Employees e=getEmployee(employees.getId());
			employees.setDate(e.getDate());
			dao.save(employees);
			return true;
		}
		else {
			if(validate(employees.getEmp_id()) && validateName(employees.getName())&&employees.getPassword().length()>=8) {
				dao.save(employees);
				return true;
			}
		}
		return false;
	}
	
	
	public Employees getEmployee(Integer id) {
		Optional<Employees> opt=dao.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null; 
	}
	
	public boolean validate(String emp_id) {
		String regx="^(BCITS)\\d{5}$";
		Pattern pattern=Pattern.compile(regx);
		Matcher matcher=pattern.matcher(emp_id);
		Employees e=dao.getEmployeeByEmpId(emp_id);
		return matcher.find()&&matcher.group().equals(emp_id)&&e==null;	
	}
	
	public boolean validateId(String emp_id) {
		String regx="^BCITS\\d{5}$";
		Pattern pattern=Pattern.compile(regx);
		Matcher matcher=pattern.matcher(emp_id);
		return matcher.find()&&matcher.group().equals(emp_id);	
	}
	
	public boolean validateName(String name) {
		String regx = "^[a-zA-Z]+(\\s+[a-zA-Z]+)*$";
		Pattern pattern=Pattern.compile(regx);
		Matcher matcher=pattern.matcher(name);
		return matcher.find()&&matcher.group().equals(name);	
	}
		
}

