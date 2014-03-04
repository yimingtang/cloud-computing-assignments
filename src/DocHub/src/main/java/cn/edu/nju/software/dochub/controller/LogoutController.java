package cn.edu.nju.software.dochub.controller;

import cn.edu.nju.software.dochub.service.UserService;
import cn.edu.nju.software.dochub.web.ResponseBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {
    UserService userService;
    ResponseBuilder responseBuilder;

    @RequestMapping(value = "/logout.html")
    public String Login(HttpServletRequest request, HttpServletResponse response,
                        ModelMap model) {
        request.getSession().removeAttribute("userAccessContext");
        return "login";
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setResponseBuilder(ResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
    }
}
