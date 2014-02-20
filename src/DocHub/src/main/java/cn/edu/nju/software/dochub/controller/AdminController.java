package cn.edu.nju.software.dochub.controller;

import cn.edu.nju.software.dochub.data.dataobject.CommentPropertyType;
import cn.edu.nju.software.dochub.data.dataobject.ReferenceType;
import cn.edu.nju.software.dochub.data.dataobject.Tag;
import cn.edu.nju.software.dochub.data.dataobject.User;
import cn.edu.nju.software.dochub.service.*;
import cn.edu.nju.software.dochub.web.ResponseBuilder;
import cn.edu.nju.software.dochub.web.UserAccessContext;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by tym on 13-12-19.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

	TagService tagService;
	UserService userService;
	CommentService commentService;
	DocumentService documentService;
	ReferenceService referenceService;
	ResponseBuilder responseBuilder;

	@RequestMapping(value = "/index.html")
	public String index(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return "redirect: user.html";
	}

	@RequestMapping(value = "/tag.html")
	public String tag(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		model.put("userAccessContext", (UserAccessContext) request.getSession()
				.getAttribute("userAccessContext"));
		model.put("tagList", tagService.getAllTags());
		return "admin/tag";
	}

	@RequestMapping(value = "/addTag.html", method = RequestMethod.POST)
	public String addTag(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String tagName = request.getParameter("name");
		Tag tag = new Tag(tagName);
		tagService.addTag(tag);
		return "redirect: tag.html";
	}

	@RequestMapping(value = "/deleteTag.html", method = RequestMethod.GET)
	public String deleteTag(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		int id = Integer.parseInt(request.getParameter("id"));
		tagService.deleteTagById(id);
		return "redirect: tag.html";
	}

	@RequestMapping(value = "/referenceType.html")
	public String referenceType(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		model.put("userAccessContext", (UserAccessContext) request.getSession()
				.getAttribute("userAccessContext"));
		model.put("referenceTypeList", referenceService.getAllReferenceTypes());
		return "admin/referenceType";
	}

	@RequestMapping(value = "/addReferenceType.html", method = RequestMethod.POST)
	public String addReferenceType(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String referenceTypeName = request.getParameter("name");
		ReferenceType referenceType = new ReferenceType(referenceTypeName);
		referenceService.addReferenceType(referenceType);
		return "redirect: referenceType.html";
	}

	@RequestMapping(value = "/deleteReferenceType.html", method = RequestMethod.GET)
	public String deleteReferenceType(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		int id = Integer.parseInt(request.getParameter("id"));
		referenceService.deleteReferenceTypeById(id);
		return "redirect: referenceType.html";
	}

	@RequestMapping(value = "/document.html")
	public String document(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		model.put("userAccessContext", (UserAccessContext) request.getSession()
				.getAttribute("userAccessContext"));
		model.put("allDocumentList", documentService.getAllDocument());
		model.put("documentTypeList", documentService.getAllDocumentType());
		model.put("dateformat", new SimpleDateFormat("yyyy.MM.dd"));
		return "admin/document";
	}

	@RequestMapping(value = "/user.html")
	public String user(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		model.put("userAccessContext", (UserAccessContext) request.getSession()
				.getAttribute("userAccessContext"));
		model.put("userList", userService.getAllUser());
		return "admin/user";
	}

	@RequestMapping(value = "/detailedcomment.html")
	public String detailedComment(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		model.put("userAccessContext", (UserAccessContext) request.getSession()
				.getAttribute("userAccessContext"));
		model.put("commentPropertyList",
				commentService.getAllCommentPropertyTypes());
		return "admin/detailedcomment";
	}

	@RequestMapping(value = "/addUser.html")
	public String addUser(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		System.out.println(">>>>>>" + request.getParameter("password"));
		user.setCreatedAt(Calendar.getInstance().getTime());
		user.setSalt("123");
		user.setName(request.getParameter("name"));
		String level = request.getParameter("permissionlevel");
		System.out.println(level);
		if (level.equals("管理员")) {
			user.setPermissionLevel((short) 0);
		} else {
			user.setPermissionLevel((short) 1);
		}

		String active = request.getParameter("state");
		if (active.equals("active")) {
			user.setActive(true);
		} else {
			user.setActive(false);
		}

		userService.addUser(user);
		return "forward:user.html";
	}

	@RequestMapping(value = "/editUser.html")
	public String editUser(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		User user = userService.getUserById(Integer.parseInt(request
				.getParameter("edituserid")));
		user.setUpdatedAt(Calendar.getInstance().getTime());
		user.setUsername(request.getParameter("editusername"));
		user.setPassword(request.getParameter("editpassword"));
		user.setName(request.getParameter("editname"));
		String level = request.getParameter("editpermissionlevel");
		System.out.println(level);
		if (level.equals("管理员")) {
			user.setPermissionLevel((short) 0);
		} else {
			user.setPermissionLevel((short) 1);
		}

		String active = request.getParameter("editstate");
		if (active.equals("active")) {
			user.setActive(true);
		} else {
			user.setActive(false);
		}

		userService.updateUser(user);
		return "forward:user.html";
	}

	@RequestMapping(value = "/getUser.aj")
	public void getUserById(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		int id = Integer.parseInt(request.getParameter("userid"));
		User user = userService.getUserById(id);
		JSONObject json = new JSONObject();
		json.put("userid", user.getId());
		json.put("username", user.getUsername());
		json.put("password", user.getPassword());
		json.put("name", user.getName());
		if (user.getPermissionLevel() == 0) {
			json.put("permissionlevel", "管理员");
		} else {
			json.put("permissionlevel", "普通用户");
		}
		if (user.getActive()) {
			json.put("state", "active");
		} else {
			json.put("state", "unactive");
		}
		responseBuilder.WriteJSONObject(response, json);
	}

	@RequestMapping(value = "/usernameExist.aj", method = RequestMethod.POST)
	public void existUser(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		User user = userService.getUserByUserName(request
				.getParameter("username"));
		boolean exist = true;
		if (user == null) {
			exist = false;
		}
		JSONObject json = new JSONObject();
		json.put("exist", exist);
		responseBuilder.WriteJSONObject(response, json);
	}

	@RequestMapping(value = "/editusernameExist.aj", method = RequestMethod.POST)
	public void editExistUser(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		User user = userService.getUserByUserName(request
				.getParameter("username"));
		int userid = Integer.parseInt(request.getParameter("userid"));
		boolean exist = true;
		if (user.getId() == userid) {
			exist = false;
		}
		JSONObject json = new JSONObject();
		json.put("exist", exist);
		responseBuilder.WriteJSONObject(response, json);
	}

	@RequestMapping(value = "/addcommentproperty.html", method = RequestMethod.POST)
	public String addCommentProperty(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String commentproperty = request.getParameter("commentproperty");
		CommentPropertyType cpt = new CommentPropertyType(commentproperty);
		commentService.addCommentPropertyType(cpt);
		return "redirect:/admin/detailedcomment.html";
	}

	@RequestMapping(value = "/deletecommentproperty.html", method = RequestMethod.GET)
	public String deleteCommentProperty(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		int id = Integer.parseInt(request.getParameter("commentpropetyid"));
		commentService.deleteCommentPropertyType(id);
		return "redirect:/admin/detailedcomment.html";
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setResponseBuilder(ResponseBuilder responseBuilder) {
		this.responseBuilder = responseBuilder;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}

}
