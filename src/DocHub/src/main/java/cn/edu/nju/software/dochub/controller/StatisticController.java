package cn.edu.nju.software.dochub.controller;

import cn.edu.nju.software.dochub.service.UserService;
import cn.edu.nju.software.dochub.web.UserAccessContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tym on 13-12-19.
 */

@Controller
@RequestMapping("/statistics")
public class StatisticController {

    UserService userService;

    @RequestMapping(value = "/index.html")
    public String Index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        model.put("userAccessContext", (UserAccessContext) request.getSession().getAttribute("userAccessContext"));
        model.put("userList", userService.getAllUser());
        return "statistics/index";
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
