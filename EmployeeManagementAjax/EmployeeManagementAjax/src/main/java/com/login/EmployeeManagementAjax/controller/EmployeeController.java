package com.login.EmployeeManagementAjax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.login.EmployeeManagementAjax.DAO.EmployeesDao;
import com.login.EmployeeManagementAjax.model.Employees;
import com.login.EmployeeManagementAjax.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeesDao dao;

	@RequestMapping({"/","/log"})
	public String load() {
		return "login";
	}
	
	@RequestMapping("/loginP")
	public  ModelAndView login(@RequestParam String emp_id,@RequestParam String password) {
		Employees employees=dao.loginValidate(emp_id, password);
		ModelAndView mv= new ModelAndView();
		if(employees!=null) {
			mv.setViewName("redirect:/in");
		}else {
			mv.addObject("errmsg","Invalid Username or Password");
			mv.setViewName("login");
		}
		return mv;
	}
	
	@RequestMapping("/add")
	public  String signin() {
			return "employeedetails";
	}
	
	@RequestMapping({"/in","/home"})
	public String index(Model model) {
		model.addAttribute("employee",dao.findAll());
		return "index";
	}
	
	@RequestMapping("/save")
	public ModelAndView save(Employees employees) {
		ModelAndView mv=new ModelAndView();
		if(!employeeService.saveOrUpdate(employees)) {
			if(!employeeService.validate(employees.getEmp_id()))
				mv.addObject("errs", "pattern not matched or dupliacte employee id");
			if(!employeeService.validateName(employees.getName()))
				mv.addObject("errn", "name should not contain numbers and  special charcaters");
			if(employees.getPassword().length()<8)
				mv.addObject("errp", "password should contain atleast 8 characters");
			mv.setViewName("employeedetails");
		}else {
			mv.setViewName("redirect:/in");
		}
		return mv;
	}
	
	@RequestMapping("/updateemployee")
	public String updateById() {
		return "demo";
	}

//	@RequestMapping("/updatebyid")
//	public ModelAndView update(@RequestParam String emp_id) {
//		Employees employees=dao.getEmployeeByEmpId(emp_id);
//		ModelAndView mv=new ModelAndView();
//		if(employees!=null) {
//			mv.addObject("employee",employees);
//			mv.setViewName("demo");
//		}else {
//			mv.addObject("errmsg","employee not found");
//			mv.setViewName("update");
//		}
//		return mv;
//	}

	@RequestMapping("/updatebyid")
	@ResponseBody
	public Employees update(@RequestParam String emp_id) {
		Employees employees=dao.getEmployeeByEmpId(emp_id);
		return employees;
		
	}
	
	@RequestMapping("/updatee")
	public @ResponseBody void updateEmployee(Employees employees) {
		employeeService.saveOrUpdate(employees);
	}
	
	@RequestMapping("/deleteemployee")
	public String deleteById() {
		return "delete";
	}
	
	@RequestMapping("/deletebyid")
	public ModelAndView delete(@RequestParam String emp_id) {
		Employees employees=dao.getEmployeeByEmpId(emp_id);
		ModelAndView mv=new ModelAndView();
		if(employees!=null) {
			mv.addObject("employee",employees);
			mv.setViewName("delete");
		}else {
			mv.addObject("errmsg","employee not found");
			mv.setViewName("delete");
		}
		return mv;
	}
	
	@RequestMapping("/delete")
	public String deleteEmployee(Employees employees) {
		dao.delete(employees);
		return "redirect:/in";
	}
	
	@RequestMapping("/display")
	public String display() {
		return "display";
	}
	
	@RequestMapping("/getemployees")
	@ResponseBody
	public Employees getEmployees(String emp_id){
		Employees employees= dao.getEmployeeByEmpId(emp_id);
		return employees;
	}
	
	@RequestMapping("/getemployee")
	@ResponseBody
	public boolean getEmployeecheck(String emp_id){
		if(employeeService.validateId(emp_id)) {
			return true;
		}
		return false;
	}	
}
