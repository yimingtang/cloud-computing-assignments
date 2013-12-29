package cn.edu.nju.software.dochub.controller;


import java.util.Calendar;
import java.util.Date;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.nju.software.dochub.data.dao.DocumentTypeDAO;
import cn.edu.nju.software.dochub.data.dataobject.Comment;
import cn.edu.nju.software.dochub.data.dataobject.Document;
import cn.edu.nju.software.dochub.data.dataobject.DocumentType;
import cn.edu.nju.software.dochub.service.CommentService;
import cn.edu.nju.software.dochub.service.DocumentService;
import cn.edu.nju.software.dochub.service.UserService;
import cn.edu.nju.software.dochub.web.ResponseBuilder;
import cn.edu.nju.software.dochub.web.UserAccessContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tym on 13-12-19.
 */

@Controller
@RequestMapping("/document")
public class DocumentController {
	
	DocumentService documentService;
	ResponseBuilder responseBuilder;
	UserService userService;
	CommentService commentService;
    @RequestMapping(value = "/index.html")
    public String index(HttpServletRequest request,
                        HttpServletResponse response, ModelMap model) {
        return "forward:/home/index.html";
    }
    
    @RequestMapping(value = "createDocument.aj")
    public void createDocument(HttpServletRequest request,
                        HttpServletResponse response, ModelMap model) {
    	Document document =new Document();
    	document.setDocumentType(documentService.findDocTypeByName((String) request.getParameter("DocType")));
    	document.setTitle(request.getParameter("Title"));
    	document.setAuthor( request.getParameter("Author"));
    	document.setAbstract_(request.getParameter("Abstract"));
    	document.setKeywords( request.getParameter("Keyword"));
    	document.setPublisher(request.getParameter("Publisher"));
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(Calendar.YEAR, Integer.parseInt(request.getParameter("PublishDate")));
    	document.setYear(calendar.getTime());
    	document.setPages((String) request.getParameter("Pages"));
    	document.setUrl((String) request.getParameter("URL"));
    	UserAccessContext uac=(UserAccessContext) request.getSession().getAttribute("userAccessContext");
    	document.setUser(userService.getUserById(uac.getUserId()));
    	documentService.addDocument(document);
    	responseBuilder.WriteJSONObject(response, new JSONObject(true));
    }

    @RequestMapping(value = "/show.html")
    public String show(HttpServletRequest request,
                       HttpServletResponse response, ModelMap model) {
    	int docid=Integer.parseInt((String)request.getParameter("docId"));
    	Document document = documentService.findDocumentById(docid);
    	model.put("document", document);
    	Calendar year =Calendar.getInstance();
    	year.setTime(document.getYear());
    	model.put("year", year.get(Calendar.YEAR));
    	model.put("userAccessContext", (UserAccessContext)request.getSession().getAttribute("userAccessContext"));
        return "document/view";
    }

    @RequestMapping(value = "/edit.html")
    public String edit(HttpServletRequest request,
                       HttpServletResponse response, ModelMap model) {
    	int docid=Integer.parseInt((String)request.getParameter("docId"));
    	Document document = documentService.findDocumentById(docid);
    	model.put("document", document);
    	Calendar year =Calendar.getInstance();
    	year.setTime(document.getYear());
    	model.put("year", year.get(Calendar.YEAR));
    	model.put("userAccessContext", (UserAccessContext)request.getSession().getAttribute("userAccessContext"));
    	model.put("documentTypeList", documentService.getAllDocumentType());
        return "document/edit";
    }
    
    @RequestMapping(value = "editDocument.aj")
    public void editDocument(HttpServletRequest request,
                        HttpServletResponse response, ModelMap model) {
    	int docid=Integer.parseInt((String)request.getParameter("DocId"));
    	Document document = documentService.findDocumentById(docid);
    	document.setDocumentType(documentService.findDocTypeByName((String) request.getParameter("DocType")));
    	document.setTitle(request.getParameter("Title"));
    	document.setAuthor( request.getParameter("Author"));
    	document.setAbstract_(request.getParameter("Abstract"));
    	document.setKeywords( request.getParameter("Keyword"));
    	document.setPublisher(request.getParameter("Publisher"));
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(Calendar.YEAR, Integer.parseInt(request.getParameter("PublishDate")));
    	document.setYear(calendar.getTime());
    	document.setPages((String) request.getParameter("Pages"));
    	document.setUrl((String) request.getParameter("URL"));
    	UserAccessContext uac=(UserAccessContext) request.getSession().getAttribute("userAccessContext");
    	document.setUser(userService.getUserById(uac.getUserId()));
    	documentService.updateDocument(document);
    	responseBuilder.WriteJSONObject(response, new JSONObject(true));
    }


    @RequestMapping(value = "/create.html")
    public String create(HttpServletRequest request,
                       HttpServletResponse response, ModelMap model) {
    	model.put("userAccessContext", (UserAccessContext)request.getSession().getAttribute("userAccessContext"));
    	model.put("documentTypeList", documentService.getAllDocumentType());
        return "document/create";
    }
    
    @RequestMapping(value = "/fuzzysearch.html")
    public String fuzzysearch(HttpServletRequest request,
                       HttpServletResponse response, ModelMap model) {
    	String fuzzyword=request.getParameter("fuzzyword");
    	if(fuzzyword==null || fuzzyword.equals("")){
    		return "forward:/home/index.html";
    	}
    	model.put("userAccessContext", (UserAccessContext)request.getSession().getAttribute("userAccessContext"));
    	model.put("allDocumentList", documentService.findDocByFuzzy(fuzzyword));
    	model.put("documentTypeList", documentService.getAllDocumentType());
        return "home";
    }
    
    @RequestMapping(value = "/accuratesearch.html")
    public String accutatesearch(HttpServletRequest request,
                       HttpServletResponse response, ModelMap model) {
    	String title=request.getParameter("searchtitle");
    	String author=request.getParameter("searchauthor");
    	String yearFromString=request.getParameter("searchdatefrom");
    	String yearToString=request.getParameter("searchdateend");
    	Date yearFrom=null;
    	Date yearTo=null;
    	Calendar calendar=Calendar.getInstance();
    	if(yearFromString !=null && !yearFromString.equals("")){
    		calendar.set(Calendar.YEAR, Integer.parseInt(yearFromString));
    		calendar.set(Calendar.MONTH, 1);
    		calendar.set(Calendar.DAY_OF_MONTH, 1);
        	yearFrom = calendar.getTime();
    	}
    	
    	if(yearToString !=null && !yearToString.equals("")){
    		calendar.set(Calendar.YEAR, Integer.parseInt(yearToString));
    		calendar.set(Calendar.MONTH, 12);
    		calendar.set(Calendar.DAY_OF_MONTH, 31);
        	yearTo = calendar.getTime();
    	}
    	
    	
    	String abstract_=request.getParameter("searchabstract");
    	String keyword=request.getParameter("searchkeyword");
    	String publisher=request.getParameter("searchpublisher");
    	String url=request.getParameter("searchurl");
    	String documenttypeString=request.getParameter("searchdoctype");
    	DocumentType documenttype=documentService.findDocTypeByName(documenttypeString);
    	
    	model.put("userAccessContext", (UserAccessContext)request.getSession().getAttribute("userAccessContext"));
    	model.put("allDocumentList", documentService.findDocByAccutate(title, author, yearFrom, yearTo, abstract_, keyword, publisher, url, documenttype));
    	model.put("documentTypeList", documentService.getAllDocumentType());
        return "home";
    }
    
    @RequestMapping(value = "/simplecomment.html")
    public String simpleComment(HttpServletRequest request,
                       HttpServletResponse response, ModelMap model) {
    	int docid=Integer.parseInt(request.getParameter("simplecomment-docid"));
    	String content=request.getParameter("simplecomment-content");
    	Document document=documentService.findDocumentById(docid);
    	
    	Comment comment =new Comment();
    	
        return "";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	public void setResponseBuilder(ResponseBuilder responseBuilder) {
		this.responseBuilder = responseBuilder;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
}
