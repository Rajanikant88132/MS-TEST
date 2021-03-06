package com.rajanikant.trial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajanikant.trial.entity.Department;




import com.rajanikant.trial.service.DepartmentService;

@RestController
@RequestMapping("/departments")

public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/{departmentId}")
	public Department findDepartmentById(@PathVariable("departmentId") Long departmentId)
	{
	System.out.println("getDepartment the Department in Controller....");
		return departmentService.findDepartmentByDepartmentId(departmentId);
	}

	@PostMapping("/")
	public Department saveDepartment(@RequestBody Department department)
	{

		System.out.println("Save Department the Department in Controller....");
		
		return departmentService.saveDepartment(department);
	}
}

