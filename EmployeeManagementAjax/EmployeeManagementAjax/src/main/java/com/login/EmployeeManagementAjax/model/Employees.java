package com.login.EmployeeManagementAjax.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.LockModeType;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


@NamedQuery(
		name="getEmployeeById",
		query="select e from Employees e where e.emp_id=?1"
		)
@NamedNativeQuery(
		name="loginN",
		query="select * from employees where emp_id=? and password=?",
		resultClass = Employees.class
		)
@NamedQuery(
		name="login",
		query="select e from Employees e where e.emp_id=?1 and e.password=?2",
		lockMode = LockModeType.NONE
		)

@Entity
@Table(name="Employees")
public class Employees {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true,nullable = false)
	private String emp_id;
	private String name;
	private String address;
	private String designation;
	private String password;
	private Double salary;
	@CreationTimestamp
	private Date date;
	@UpdateTimestamp
	private Date update;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getUpdate() {
		return update;
	}
	public void setUpdate(Date update) {
		this.update = update;
	}
	
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + ", designation=" + designation
				+ ", salary=" + salary + ", date=" + date + "]";
	}
	
}
