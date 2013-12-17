package cn.edu.nju.software.dochub.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.nju.software.dochub.service.LoginService;

@Controller
public class LoginController {
	LoginService loginService;
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String Login(HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		return "login";
	}
	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
		System.out.println(">>>>>>>>>>loginservice");
	}
}
