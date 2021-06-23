package com.rajanikant.trial.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController


public class FallbackMethodController {

	@GetMapping("/userServiceFallBack")
	public String userServiceFallBackMehtod()
	{
		return "User service is taking time more time ";
	}
	
	@GetMapping("/departmentServiceFallBack")
	public String departmentServiceFallBackMehtod()
	{
		return "Department service is taking time more time ";
	}
}
