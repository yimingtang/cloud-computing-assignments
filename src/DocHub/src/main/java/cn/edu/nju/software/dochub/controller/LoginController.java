package cn.edu.nju.software.dochub.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.nju.software.dochub.data.dataobject.User;
import cn.edu.nju.software.dochub.service.LoginService;
import cn.edu.nju.software.dochub.web.ResponseBuilder;
import cn.edu.nju.software.dochub.web.UserAccessContext;

@Controller
public class LoginController {
    LoginService loginService;
    ResponseBuilder responseBuilder;

    @RequestMapping(value = "/login.aj", method = RequestMethod.POST)
    public void Login(HttpServletRequest request,
                      HttpServletResponse response, ModelMap model) {
        UserAccessContext userAccessContext = (UserAccessContext)request.getSession().getAttribute("userAccessContext");
        if(userAccessContext!=null){
        	userAccessContext =null;
        	request.getSession().removeAttribute("userAccessContext");
        }
        
        String flag = "wrong";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = loginService.getUserByName(username);
        if (user == null) {
            flag = "notexist";
        } else if (user.getPassword().equals(password)) {
            flag = "success";
            userAccessContext = new UserAccessContext(user.getName(), user.getName(),user.getId(), user.getPermissionLevel());
            request.getSession().setAttribute("userAccessContext", userAccessContext);
        }
        JSONObject json = new JSONObject();
        json.put("flag", flag);

        responseBuilder.WriteJSONObject(response, json);
    }

    @RequestMapping(value = "/login.html")
    public String LoginShow(HttpServletRequest request,
                       HttpServletResponse response, ModelMap model) {
        return "login";
    }
    
    @RequestMapping(value = "/home/index.html")
    public String Home(HttpServletRequest request,
                       HttpServletResponse response, ModelMap model) {
    	model.put("userAccessContext", (UserAccessContext)request.getSession().getAttribute("userAccessContext"));
        return "home";
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
