package com.algaworks.algafood.core.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {

	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("pages/login");
	}
	
	@GetMapping("/oauth/confirm_access")
	public ModelAndView approval() {
		return new ModelAndView("pages/approval");
	}
}
