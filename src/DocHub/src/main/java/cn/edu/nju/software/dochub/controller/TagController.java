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
public class TagController {

    @RequestMapping(value = "/tag/index.html")
    public String index(HttpServletRequest request,
                        HttpServletResponse response, ModelMap model) {
        return "admin_tag";
    }

    @RequestMapping(value = "/admin/tag/index.html")
    public String all(HttpServletRequest request,
                        HttpServletResponse response, ModelMap model) {
        return "admin_tag";
    }
}
