package com.nnk.springboot.controllers;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
	@RequestMapping("/")
	public String home(Model model)
	{
		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{
		return "redirect:/bidList/list";
	}

	@RequestMapping("/403")
	public void errorPage(Model model)
	{

	}

	@GetMapping("/loginsuccessful")
	public String getAllUserArticles(@AuthenticationPrincipal UserDetails userDetails) {
		String role = null;
		if (!userDetails.getAuthorities().isEmpty()) {
			GrantedAuthority authority = userDetails.getAuthorities().iterator().next();
			role = authority.getAuthority();  // Exemple : "ROLE_USER"
		}
		if(role.equals("ROLE_ADMIN")) {
			return "redirect:/admin/home";
		}else{
			return "redirect:/trade/list";
		}
	}
}
