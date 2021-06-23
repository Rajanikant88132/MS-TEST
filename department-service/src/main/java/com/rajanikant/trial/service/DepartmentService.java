package com.rajanikant.trial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajanikant.trial.entity.Department;




import com.rajanikant.trial.repository.DepartmentRepository;

@Service

public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	public Department saveDepartment(Department department)
	{
		System.out.println("Save the Department in Service....");
		return departmentRepository.save(department);
	}
	
	public Department findDepartmentByDepartmentId(Long departmentId)
	{
		System.out.println("getDepartment the Department in Service....");
		return departmentRepository.findDepartmentByDepartmentId(departmentId);
	}

}
