package com.authentication_web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		
		return "home";
	}

	@GetMapping("/admin")
	public String admin() {

		return "admin_page";
	}

}
