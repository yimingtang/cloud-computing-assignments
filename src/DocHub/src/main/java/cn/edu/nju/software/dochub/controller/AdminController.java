package cn.edu.nju.software.dochub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tym on 13-12-19.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(value = "/index.html")
    public String Index(HttpServletRequest request,
                        HttpServletResponse response, ModelMap model) {
        return "admin/user";
    }

    @RequestMapping(value = "/tag.html")
    public String Tag(HttpServletRequest request,
                        HttpServletResponse response, ModelMap model) {
        return "admin/tag";
    }
    
    @RequestMapping(value = "/user.html")
    public String User(HttpServletRequest request,
                        HttpServletResponse response, ModelMap model) {
        return "admin/user";
    }
 
}
