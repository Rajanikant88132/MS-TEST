package com.rajanikant.trial.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajanikant.trial.user.VO.ResponseTemplateVo;
import com.rajanikant.trial.user.entity.User;
import com.rajanikant.trial.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	public UserService userService;
	
    @PostMapping("/")
	public User saveUSer(@RequestBody User user)
	{
    	System.out.println("Inside User Controller.. Save ......");
		return userService.saveUser(user);
	}
    
    @GetMapping("/{userId}")
    public ResponseTemplateVo getUserwithDepartment(@PathVariable Long userId)
    {
    	System.out.println(" Inside Controller userId  :"+userId);
    	return userService.getUserwithDepartment(userId);
    }
}
