package com.rajanikant.trial.repository;

import org.springframework.stereotype.Repository;

import com.rajanikant.trial.entity.*;

import org.springframework.data.jpa.repository.*;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long>  {

	public Department findDepartmentByDepartmentId(Long departmentid);
}
