package cn.edu.nju.software.dochub.controller;

import cn.edu.nju.software.dochub.data.dataobject.User;
import cn.edu.nju.software.dochub.service.DocumentService;
import cn.edu.nju.software.dochub.service.UserService;
import cn.edu.nju.software.dochub.web.ResponseBuilder;
import cn.edu.nju.software.dochub.web.UserAccessContext;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    UserService userService;
    ResponseBuilder responseBuilder;
    DocumentService documentService;

    @RequestMapping(value = "/login.aj", method = RequestMethod.POST)
    public void Login(HttpServletRequest request,
                      HttpServletResponse response, ModelMap model) {
        UserAccessContext userAccessContext = (UserAccessContext) request.getSession().getAttribute("userAccessContext");
        if (userAccessContext != null) {
            userAccessContext = null;
            request.getSession().removeAttribute("userAccessContext");
        }

        String flag = "wrong";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.getUserByUserName(username);
        if (user == null) {
            flag = "notexist";
        } else if (!user.getActive()) {
            flag = "unactive";
        } else if (user.getPassword().equals(password)) {
            flag = "success";
            userAccessContext = new UserAccessContext(user.getUsername(), user.getName(), user.getId(), user.getPermissionLevel());
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
        model.put("userAccessContext", (UserAccessContext) request.getSession()
                .getAttribute("userAccessContext"));
        model.put("allDocumentList", documentService.getAllDocument());
        model.put("documentTypeList", documentService.getAllDocumentType());
        return "home";
    }

    public void setResponseBuilder(ResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
        System.out.println(">>>>>>>>>>responseBuilder");
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
}
