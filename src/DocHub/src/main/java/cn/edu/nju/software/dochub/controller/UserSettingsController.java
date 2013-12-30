package cn.edu.nju.software.dochub.controller;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.nju.software.dochub.data.dataobject.User;
import cn.edu.nju.software.dochub.service.UserService;
import cn.edu.nju.software.dochub.web.ResponseBuilder;
import cn.edu.nju.software.dochub.web.UserAccessContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tym on 13-12-19.
 */

@Controller
@RequestMapping("/settings")
public class UserSettingsController {
	UserService userService;
	ResponseBuilder responseBuilder;
	
	@RequestMapping(value = "/")
	public String home(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return "forward:/settings/index.html";
	}

	@RequestMapping(value = "/index.html")
	public String index(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		model.put("userAccessContext", (UserAccessContext) request.getSession()
				.getAttribute("userAccessContext"));
		return "settings/settings";
	}

	@RequestMapping(value = "/checkpassword.aj")
	public void checkPassword(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		UserAccessContext uac = (UserAccessContext) request.getSession()
				.getAttribute("userAccessContext");
		String flag="failure";
		String password=request.getParameter("password");
		User user=userService.getUserById(uac.getUserId());
		if(password!=null && password.equals(user.getPassword())){
			flag="success";
		}
		System.out.println(flag);
		JSONObject json=new JSONObject();
		json.put("flag", flag);
		responseBuilder.WriteJSONObject(response, json);
		
	}
	
	@RequestMapping(value = "/changepassword.html")
	public String changePassword(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		UserAccessContext uac = (UserAccessContext) request.getSession()
				.getAttribute("userAccessContext");
		String newpassword=request.getParameter("newPassword");
		User user=userService.getUserById(uac.getUserId());
		user.setPassword(newpassword);
		userService.updateUser(user);
		request.getSession().removeAttribute("userAccessContext");
		return "redirect:/login.html";
	}
	
	
	
	
	
	
	
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setResponseBuilder(ResponseBuilder responseBuilder) {
		this.responseBuilder = responseBuilder;
	}
}
