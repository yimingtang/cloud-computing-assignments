package cn.edu.nju.software.dochub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.nju.software.dochub.web.UserAccessContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tym on 13-12-19.
 */

@Controller
@RequestMapping("/document")
public class DocumentController {

    @RequestMapping(value = "/index.html")
    public String index(HttpServletRequest request,
                        HttpServletResponse response, ModelMap model) {
        return "home";
    }

    @RequestMapping(value = "/show.html")
    public String show(HttpServletRequest request,
                       HttpServletResponse response, ModelMap model) {
        return "document/view";
    }

    @RequestMapping(value = "/edit.html")
    public String edit(HttpServletRequest request,
                       HttpServletResponse response, ModelMap model) {
        return "document/edit";
    }

    @RequestMapping(value = "/create.html")
    public String create(HttpServletRequest request,
                       HttpServletResponse response, ModelMap model) {
    	model.put("userAccessContext", (UserAccessContext)request.getSession().getAttribute("userAccessContext"));
        return "document/create";
    }
}
