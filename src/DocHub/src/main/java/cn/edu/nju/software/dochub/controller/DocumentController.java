package cn.edu.nju.software.dochub.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.tools.generic.DateTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.edu.nju.software.dochub.data.dao.DocumentTypeDAO;
import cn.edu.nju.software.dochub.data.dataobject.Attachment;
import cn.edu.nju.software.dochub.data.dataobject.Comment;
import cn.edu.nju.software.dochub.data.dataobject.CommentProperty;
import cn.edu.nju.software.dochub.data.dataobject.CommentPropertyType;
import cn.edu.nju.software.dochub.data.dataobject.Document;
import cn.edu.nju.software.dochub.data.dataobject.DocumentType;
import cn.edu.nju.software.dochub.data.dataobject.Reference;
import cn.edu.nju.software.dochub.data.dataobject.ReferenceType;
import cn.edu.nju.software.dochub.data.dataobject.User;
import cn.edu.nju.software.dochub.service.AttachmentService;
import cn.edu.nju.software.dochub.service.CommentService;
import cn.edu.nju.software.dochub.service.DocumentService;
import cn.edu.nju.software.dochub.service.ReferenceService;
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
	AttachmentService attachmentService;
	ReferenceService referenceService;
	
	@RequestMapping(value = "/index.html")
	public String index(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return "forward:/home/index.html";
	}

	@RequestMapping(value = "createDocument.aj")
	public void createDocument(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Document document = new Document();
		document.setDocumentType(documentService
				.findDocTypeByName((String) request.getParameter("DocType")));
		document.setCreatedAt(Calendar.getInstance().getTime());
		document.setUpdatedAt(Calendar.getInstance().getTime());
		document.setTitle(request.getParameter("Title"));
		document.setAuthor(request.getParameter("Author"));
		document.setAbstract_(request.getParameter("Abstract"));
		document.setKeywords(request.getParameter("Keyword"));
		document.setPublisher(request.getParameter("Publisher"));
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,
				Integer.parseInt(request.getParameter("PublishDate")));
		document.setYear(calendar.getTime());
		document.setPages((String) request.getParameter("Pages"));
		document.setUrl((String) request.getParameter("URL"));
		UserAccessContext uac = (UserAccessContext) request.getSession()
				.getAttribute("userAccessContext");
		document.setUser(userService.getUserById(uac.getUserId()));
		documentService.addDocument(document);
		responseBuilder.WriteJSONObject(response, new JSONObject(true));
	}

	@RequestMapping(value = "/show.html")
	public String show(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		int docid = Integer.parseInt((String) request.getParameter("docId"));
		Document document = documentService.findDocumentById(docid);
		model.put("document", document);
		Calendar year = Calendar.getInstance();
		year.setTime(document.getYear());
		model.put("year", year.get(Calendar.YEAR));
		model.put("userAccessContext", (UserAccessContext) request.getSession()
				.getAttribute("userAccessContext"));
		model.put("dateformat",
				new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss"));
		List<Comment> commentlist = commentService
				.findAllPublishedCommentByDocId(docid);
		System.out.println("docid:" + docid);
		System.out.println("commentsize:" + commentlist.size());
		model.put("commentList", commentlist);
		model.put("commentPropertyList",
				commentService.getAllCommentPropertyTypes());
		return "document/view";
	}

	@RequestMapping(value = "/edit.html")
	public String edit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		int docid = Integer.parseInt((String) request.getParameter("docId"));
		Document document = documentService.findDocumentById(docid);
		model.put("document", document);
		Calendar year = Calendar.getInstance();
		year.setTime(document.getYear());
		model.put("year", year.get(Calendar.YEAR));
		model.put("userAccessContext", (UserAccessContext) request.getSession()
				.getAttribute("userAccessContext"));
		model.put("documentTypeList", documentService.getAllDocumentType());
		return "document/edit";
	}

	@RequestMapping(value = "editDocument.aj")
	public void editDocument(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		int docid = Integer.parseInt((String) request.getParameter("DocId"));
		Document document = documentService.findDocumentById(docid);
		document.setUpdatedAt(Calendar.getInstance().getTime());
		document.setDocumentType(documentService
				.findDocTypeByName((String) request.getParameter("DocType")));
		document.setTitle(request.getParameter("Title"));
		document.setAuthor(request.getParameter("Author"));
		document.setAbstract_(request.getParameter("Abstract"));
		document.setKeywords(request.getParameter("Keyword"));
		document.setPublisher(request.getParameter("Publisher"));
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,
				Integer.parseInt(request.getParameter("PublishDate")));
		document.setYear(calendar.getTime());
		document.setPages((String) request.getParameter("Pages"));
		document.setUrl((String) request.getParameter("URL"));
		UserAccessContext uac = (UserAccessContext) request.getSession()
				.getAttribute("userAccessContext");
		document.setUser(userService.getUserById(uac.getUserId()));
		documentService.updateDocument(document);
		responseBuilder.WriteJSONObject(response, new JSONObject(true));
	}

	@RequestMapping(value = "/create.html")
	public String create(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		model.put("userAccessContext", (UserAccessContext) request.getSession()
				.getAttribute("userAccessContext"));
		model.put("documentTypeList", documentService.getAllDocumentType());
		return "document/create";
	}

	@RequestMapping(value = "/fuzzysearch.html")
	public String fuzzysearch(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String fuzzyword = request.getParameter("fuzzyword");
		if (fuzzyword == null || fuzzyword.equals("")) {
			return "forward:/home/index.html";
		}
		model.put("userAccessContext", (UserAccessContext) request.getSession()
				.getAttribute("userAccessContext"));
		model.put("allDocumentList", documentService.findDocByFuzzy(fuzzyword));
		model.put("documentTypeList", documentService.getAllDocumentType());
		return "home";
	}
	
	@RequestMapping(value = "/fuzzysearch.aj")
	public void fuzzysearchAj(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String fuzzyword = request.getParameter("fuzzyword");
		int docid=Integer.parseInt(request.getParameter("docId"));
		
		if (fuzzyword == null || fuzzyword.equals("")) {
			
		}
		
		JSONObject json=new JSONObject();
		List<Document> docList=documentService.findDocByFuzzy(fuzzyword);
		int size=docList.size();
		int docsize=size;
		int index=0;
		for(int i=0;i<docsize;i++){
			Document document =docList.get(i);
			if(document.getId()==docid){
				size--;
				continue;
			}
			
			JSONObject jsontmp=new JSONObject();
			jsontmp.put("id", document.getId());
			jsontmp.put("name", document.getTitle());
			ReferenceType referenceType=referenceService.getReferenceTypeByDocs(documentService.findDocumentById(docid), document);
			
			if(referenceType!=null){
				jsontmp.put("type",referenceType.getName());
			}else{
				jsontmp.put("type", "没有关系");
			}
			
			json.put(index,jsontmp);
			index++;
		}
		json.put("size",size);
		
		List<ReferenceType> referenceList=referenceService.getAllReferenceTypes();
		int rsize=referenceList.size();
		JSONObject rJson=new JSONObject();
		rJson.put("size", rsize+1);
		rJson.put(0, "没有关系");
		for(int i= 1;i<=rsize;i++){
			rJson.put(i, referenceList.get(i-1).getName());
		}
		json.put("type",rJson);
		
		
		responseBuilder.WriteJSONObject(response, json);
	}

	@RequestMapping(value = "/accuratesearch.html")
	public String accutatesearch(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String title = request.getParameter("searchtitle");
		String author = request.getParameter("searchauthor");
		String yearFromString = request.getParameter("searchdatefrom");
		String yearToString = request.getParameter("searchdateend");
		Date yearFrom = null;
		Date yearTo = null;
		Calendar calendar = Calendar.getInstance();
		if (yearFromString != null && !yearFromString.equals("")) {
			calendar.set(Calendar.YEAR, Integer.parseInt(yearFromString));
			calendar.set(Calendar.MONTH, 1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			yearFrom = calendar.getTime();
		}

		if (yearToString != null && !yearToString.equals("")) {
			calendar.set(Calendar.YEAR, Integer.parseInt(yearToString));
			calendar.set(Calendar.MONTH, 12);
			calendar.set(Calendar.DAY_OF_MONTH, 31);
			yearTo = calendar.getTime();
		}

		String abstract_ = request.getParameter("searchabstract");
		String keyword = request.getParameter("searchkeyword");
		String publisher = request.getParameter("searchpublisher");
		String url = request.getParameter("searchurl");
		String documenttypeString = request.getParameter("searchdoctype");
		DocumentType documenttype = documentService
				.findDocTypeByName(documenttypeString);

		model.put("userAccessContext", (UserAccessContext) request.getSession()
				.getAttribute("userAccessContext"));
		model.put("allDocumentList", documentService.findDocByAccutate(title,
				author, yearFrom, yearTo, abstract_, keyword, publisher, url,
				documenttype));
		model.put("documentTypeList", documentService.getAllDocumentType());
		return "home";
	}

	@RequestMapping(value = "/simplecomment.html")
	public String simpleComment(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		int docid = Integer.parseInt(request
				.getParameter("simplecomment-docid"));
		String content = request.getParameter("simplecomment-content");
		Document document = documentService.findDocumentById(docid);
		User user = userService.getUserById(((UserAccessContext) request
				.getSession().getAttribute("userAccessContext")).getUserId());

		Comment comment = new Comment();
		comment.setCreatedAt(Calendar.getInstance().getTime());
		comment.setUpdatedAt(Calendar.getInstance().getTime());
		comment.setPublished(true);
		comment.setType(0);// 0 for simple
		comment.setContent(content);
		comment.setDocument(document);
		comment.setUser(user);

		commentService.addSimpleComment(comment);
		return "redirect:/document/show.html?docId=" + docid;
	}

	@RequestMapping(value = "/simpledraft.aj")
	public void simpleDraft(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		int docid = Integer.parseInt(request.getParameter("docid"));
		String content = request.getParameter("content");
		Document document = documentService.findDocumentById(docid);
		User user = userService.getUserById(((UserAccessContext) request
				.getSession().getAttribute("userAccessContext")).getUserId());

		Comment comment = new Comment();
		comment.setCreatedAt(Calendar.getInstance().getTime());
		comment.setUpdatedAt(Calendar.getInstance().getTime());
		comment.setPublished(false);
		comment.setType(0);// 0 for simple
		comment.setContent(content);
		comment.setDocument(document);
		comment.setUser(user);
		commentService.addSimpleComment(comment);
		responseBuilder.WriteJSONObject(response, new JSONObject(true));
	}

	@RequestMapping(value = "/detailedcomment.html")
	public String detailedComment(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		int docid = Integer.parseInt(request
				.getParameter("detailedcomment-docid"));
		Document document = documentService.findDocumentById(docid);
		User user = userService.getUserById(((UserAccessContext) request
				.getSession().getAttribute("userAccessContext")).getUserId());

		Comment comment = new Comment();
		comment.setType(1);// 1 for detailed
		comment.setCreatedAt(Calendar.getInstance().getTime());
		comment.setUpdatedAt(Calendar.getInstance().getTime());
		comment.setPublished(true);
		comment.setContent("");
		comment.setDocument(document);
		comment.setUser(user);

		boolean allnull = true;
		List<CommentPropertyType> cptlist = commentService
				.getAllCommentPropertyTypes();
		for (CommentPropertyType cpt : cptlist) {
			String value = request.getParameter(cpt.getName());
			System.out.println(">>>>" + cpt.getName() + "=" + value);
			if (value != null && !value.equals("")) {
				allnull = false;
			}
		}
		if (!allnull) {
			commentService.addSimpleComment(comment);
		}

		for (CommentPropertyType cpt : cptlist) {
			String value = request.getParameter(cpt.getName());
			System.out.println(">>>>" + cpt.getName() + "=" + value);
			if (value != null && !value.equals("")) {
				CommentProperty cp = new CommentProperty(comment, cpt, value);
				commentService.addCommentProperty(cp);
			}
		}
		return "redirect:/document/show.html?docId=" + docid;
	}

	@RequestMapping(value = "/detailedcomment.aj")
	public void detailedCommentDraft(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		int docid = Integer.parseInt(request
				.getParameter("detailedcomment-docid"));
		Document document = documentService.findDocumentById(docid);
		User user = userService.getUserById(((UserAccessContext) request
				.getSession().getAttribute("userAccessContext")).getUserId());

		Comment comment = new Comment();
		comment.setType(1);// 1 for detailed
		comment.setCreatedAt(Calendar.getInstance().getTime());
		comment.setUpdatedAt(Calendar.getInstance().getTime());
		comment.setPublished(false);
		comment.setContent("");
		comment.setDocument(document);
		comment.setUser(user);

		boolean allnull = true;
		List<CommentPropertyType> cptlist = commentService
				.getAllCommentPropertyTypes();
		for (CommentPropertyType cpt : cptlist) {
			String value = request.getParameter(cpt.getName());
			System.out.println(">>>>" + cpt.getName() + "=" + value);
			if (value != null && !value.equals("")) {
				allnull = false;
			}
		}
		if (!allnull) {
			commentService.addSimpleComment(comment);
		}

		for (CommentPropertyType cpt : cptlist) {
			String value = request.getParameter(cpt.getName());
			System.out.println(">>>>" + cpt.getName() + "=" + value);
			if (value != null && !value.equals("")) {
				CommentProperty cp = new CommentProperty(comment, cpt, value);
				commentService.addCommentProperty(cp);
			}
		}
		responseBuilder.WriteJSONObject(response, new JSONObject(true));
	}

	@RequestMapping(value = "/mydocument.html")
	public String myDocument(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		UserAccessContext uac = (UserAccessContext) request.getSession()
				.getAttribute("userAccessContext");
		model.put("userAccessContext", uac);

		model.put("allDocumentList",
				documentService.getAllDocumentByUserId(uac.getUserId()));
		model.put("documentTypeList", documentService.getAllDocumentType());
		model.put("dateformat", new SimpleDateFormat("yyyy.MM.dd"));
		return "/document/documents";
	}

	@RequestMapping(value = "/alldocument.html")
	public String allDocument(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		model.put("userAccessContext", (UserAccessContext) request.getSession()
				.getAttribute("userAccessContext"));
		model.put("allDocumentList", documentService.getAllDocument());
		model.put("documentTypeList", documentService.getAllDocumentType());
		model.put("dateformat", new SimpleDateFormat("yyyy.MM.dd"));
		return "/document/documents";
	}

	@RequestMapping(value = "/mycommentdocument.html")
	public String myCommentDocument(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		UserAccessContext uac = (UserAccessContext) request.getSession()
				.getAttribute("userAccessContext");
		model.put("userAccessContext", uac);

		model.put("allDocumentList", documentService
				.getAllUserCommentedDocumentByUserId(uac.getUserId()));
		model.put("documentTypeList", documentService.getAllDocumentType());
		model.put("dateformat", new SimpleDateFormat("yyyy.MM.dd"));
		return "/document/documents";
	}

	@RequestMapping(value = "/deletedocument.aj")
	public void deleteDocument(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		int docid = Integer.parseInt(request.getParameter("docId"));
		documentService.deleteDocument(docid);
		responseBuilder.WriteJSONObject(response, new JSONObject(true));
	}

	@RequestMapping(value = "/addattachment.aj")
	public void addAttachment(HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
			throws UnsupportedEncodingException {
		MultipartHttpServletRequest mphsrequest = (MultipartHttpServletRequest) request;
		int docid = Integer.parseInt(mphsrequest.getParameter("docId"));
		UserAccessContext uac = (UserAccessContext) request.getSession()
				.getAttribute("userAccessContext");
		MultipartFile mpfile = mphsrequest.getFile("inputfileupload");
		String flag="error";
		Attachment attachment = attachmentService
				.findAttachmentByDocumentIdAndAttachmentName(docid,
						mpfile.getOriginalFilename());

		if (attachment==null) {
			String originFileName=mpfile.getOriginalFilename();
			String ext = originFileName.substring(originFileName.lastIndexOf(".") + 1,  
					originFileName.length()); 
			String absoluteDirectoryString = DocumentService.absoluteAttachmentDir
					+ docid;
			String relativeDirectoryString = DocumentService.relativeAttachmentDir
					+ docid;
			String randomfilename=String.valueOf(new Random(Calendar.getInstance().getTimeInMillis()).nextLong())+"."+ext;
			String filepath = absoluteDirectoryString + File.separator
					+ randomfilename;
			String relativefilepath = relativeDirectoryString + File.separator
					+ randomfilename;
			File file = new File(filepath);
			File directory = new File(absoluteDirectoryString);
			directory.mkdirs();
			try {
				file.createNewFile();
				mpfile.transferTo(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			attachment = new Attachment();
			attachment.setCreatedAt(Calendar.getInstance().getTime());
			attachment.setUpdatedAt(Calendar.getInstance().getTime());
			attachment.setName(mpfile.getOriginalFilename());
			attachment.setUrl(relativefilepath);
			attachment.setType((short) 1);// 1 default
			attachment.setDocument(documentService.findDocumentById(docid));
			attachment.setUser(userService.getUserById(uac.getUserId()));

			attachmentService.addAttachment(attachment);
			flag="new";

		} else {
			String absolutefilepath=System.getProperty("project.root")+attachment.getUrl();
			File file=new File(absolutefilepath);
			try {
				mpfile.transferTo(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			attachment.setUpdatedAt(Calendar.getInstance().getTime());
			attachment.setUser(userService.getUserById(uac.getUserId()));

			attachmentService.updateAttachment(attachment);
			flag="update";
		}

		Attachment lastAttachment = attachmentService
				.findAttachmentByDocumentIdAndAttachmentName(docid,
						mpfile.getOriginalFilename());
		JSONObject json = new JSONObject();
		json.put("flag", flag);
		json.put("attachmentid", lastAttachment.getId());
		json.put("name", lastAttachment.getName());
		json.put("url", lastAttachment.getUrl());
		responseBuilder.WriteJSONObject(response, json);
	}

	@RequestMapping(value = "/deleteAttachment.aj")
	public void deleteAttachment(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		int attachmentid = Integer.parseInt(request
				.getParameter("attachmentId"));
		Attachment attachment =attachmentService.findAttachmentById(attachmentid);
		String url=attachment.getUrl();
		attachmentService.deleteAttachmentById(attachmentid);
		
		String filepath=System.getProperty("project.root")+url;
		File file=new File(filepath);
		file.delete();
		responseBuilder.WriteJSONObject(response, new JSONObject(true));
	}
	
	@RequestMapping(value = "/downloadAttachment.aj")
	public void downloadAttachment(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		int attachmentid = Integer.parseInt(request
				.getParameter("attachmentId"));
		Attachment attachment =attachmentService.findAttachmentById(attachmentid);
		String url=attachment.getUrl();
		String filepath=System.getProperty("project.root")+url;
		
		OutputStream os = response.getOutputStream();
		response.reset();
		response.setContentType("application/octet-stream; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(attachment.getName(), "UTF-8"));
		System.out.println("file>>>>>>>>>>>>>>>>>>>>"+attachment.getName());
		File pfile = new File(filepath);
		os.write(FileUtils.readFileToByteArray(pfile));
		
		responseBuilder.WriteJSONObject(response, new JSONObject(true));
	}
	
	@RequestMapping(value = "/changereference.aj")
	public void changeReference(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		int sourceid = Integer.parseInt(request.getParameter("sourceId"));
		int destid = Integer.parseInt(request.getParameter("destId"));
		String referenceName = request.getParameter("referenceType");
		String flag="null";
		JSONObject json=new JSONObject();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+destid);
		Document srcDoc=documentService.findDocumentById(sourceid);
		Document destDoc=documentService.findDocumentById(destid);
		
		if(referenceName.equals("没有关系")){
			referenceService.deleteReference(srcDoc, destDoc);
			flag="delete";
		}else{
			Reference reference=referenceService.getReferenceByDocs(srcDoc, destDoc);
			ReferenceType referenceType=referenceService.getReferenceTypeByName(referenceName);
			if(reference==null){
				referenceService.addReference(srcDoc, destDoc, referenceType);
				flag="add";
				reference=referenceService.getReferenceByDocs(srcDoc, destDoc);
			}else{
				reference.setReferenceType(referenceType);
				referenceService.updateReference(reference);
				flag="update";
			}
			
			json.put("referenceName", referenceType.getName());
			json.put("referencId", reference.getId());
		}
		
		
		json.put("flag", flag);
		json.put("destId", destid);
		json.put("title", destDoc.getTitle());
		
		responseBuilder.WriteJSONObject(response, json);
	}
	
	@RequestMapping(value = "/deletereference.aj")
	public void deleteReference(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		int referenceid = Integer.parseInt(request.getParameter("referenceId"));
		referenceService.deleteReferenceById(referenceid);
		
		responseBuilder.WriteJSONObject(response, new JSONObject(true));
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

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}
}
