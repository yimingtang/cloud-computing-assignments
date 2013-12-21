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
@RequestMapping("/settings")
public class UserSettingsController {

    @RequestMapping(value = "/")
    public String home(HttpServletRequest request,
                        HttpServletResponse response, ModelMap model) {
        return "settings/settings";
    }

    @RequestMapping(value = "/index.html")
    public String index(HttpServletRequest request,
                        HttpServletResponse response, ModelMap model) {
        return "settings/settings";
    }
}
