package com.rajanikant.trial.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rajanikant.trial.user.VO.Department;
import com.rajanikant.trial.user.VO.ResponseTemplateVo;
import com.rajanikant.trial.user.entity.User;
import com.rajanikant.trial.user.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public User saveUser(User user)
	{
		
		System.out.println("Inside User service.. Save ......");
		return userRepository.save(user);
	}

	public ResponseTemplateVo getUserwithDepartment(Long userId) {
		// TODO Auto-generated method stub
		
		ResponseTemplateVo repsonseVo=new ResponseTemplateVo();
		User user=userRepository.findUserByUserId(userId);
		System.out.println(" userId :"+userId);
		
		System.out.println(" Found Department  :"+user.getDepartmentId());
		Department department=restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+
		user.getDepartmentId(), Department.class);
		
		System.out.println(" department found from Department Service for userId  :"+department);
		repsonseVo.setUser(user);
		
		repsonseVo.setDepartment(department);
		return repsonseVo;
	}

}
