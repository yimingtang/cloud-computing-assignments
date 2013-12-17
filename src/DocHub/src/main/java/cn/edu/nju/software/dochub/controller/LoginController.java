package cn.edu.nju.software.dochub.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.nju.software.dochub.data.dataobject.User;
import cn.edu.nju.software.dochub.service.LoginService;
import cn.edu.nju.software.dochub.web.ResponseBuilder;

@Controller
public class LoginController {
	LoginService loginService;
	ResponseBuilder responseBuilder;
	
	@RequestMapping(value = "/login.aj", method = RequestMethod.POST)
	public void Login(HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		String flag="wrong";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user=loginService.getUserByName(username);
		if(user == null){
			flag="notexist";
		}else if(user.getPassword().equals(password)){
			flag="success";
		}
		JSONObject json=new JSONObject();
		json.put("flag", flag);
		
		responseBuilder.WriteJSONObject(response, json);
	}
	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
		System.out.println(">>>>>>>>>>loginservice");
	}

	public void setResponseBuilder(ResponseBuilder responseBuilder) {
		this.responseBuilder = responseBuilder;
		System.out.println(">>>>>>>>>>responseBuilder");
	}
}
