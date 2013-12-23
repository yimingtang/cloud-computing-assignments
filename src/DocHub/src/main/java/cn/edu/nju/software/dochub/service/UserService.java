package cn.edu.nju.software.dochub.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.nju.software.dochub.data.*;
import cn.edu.nju.software.dochub.data.dao.AttachmentDAO;
import cn.edu.nju.software.dochub.data.dao.CommentDAO;
import cn.edu.nju.software.dochub.data.dao.CommentPropertyDAO;
import cn.edu.nju.software.dochub.data.dao.CommentPropertyTypeDAO;
import cn.edu.nju.software.dochub.data.dao.DocumentDAO;
import cn.edu.nju.software.dochub.data.dao.DocumentHasDocumentPropertyDAO;
import cn.edu.nju.software.dochub.data.dao.DocumentPropertyDAO;
import cn.edu.nju.software.dochub.data.dao.DocumentPropertyTypeDAO;
import cn.edu.nju.software.dochub.data.dao.DocumentTypeDAO;
import cn.edu.nju.software.dochub.data.dao.DocumentTypeHasDocumentPropertyTypeDAO;
import cn.edu.nju.software.dochub.data.dao.ReferenceDAO;
import cn.edu.nju.software.dochub.data.dao.ReferenceTypeDAO;
import cn.edu.nju.software.dochub.data.dao.TagDAO;
import cn.edu.nju.software.dochub.data.dao.TaggingDAO;
import cn.edu.nju.software.dochub.data.dao.UserDAO;
import cn.edu.nju.software.dochub.data.dao.UserLogDAO;
import cn.edu.nju.software.dochub.data.dataobject.User;

public class UserService {

	DocumentTypeDAO documentTypeDAO;
	CommentPropertyDAO commentPropertyDAO;
	AttachmentDAO attachmentDAO;
	CommentDAO commentDAO;
	DocumentTypeHasDocumentPropertyTypeDAO documentTypeHasDocumentPropertyTypeDAO;
	UserLogDAO userLogDAO;
	DocumentPropertyTypeDAO documentPropertyTypeDAO;
	UserDAO userDAO;
	TagDAO tagDAO;
	ReferenceDAO referenceDAO;
	ReferenceTypeDAO referenceTypeDAO;
	DocumentDAO documentDAO;
	CommentPropertyTypeDAO commentPropertyTypeDAO;
	TaggingDAO taggingDAO;
	DocumentHasDocumentPropertyDAO documentHasDocumentPropertyDAO;
	DocumentPropertyDAO documentPropertyDAO;

	public User getUserByUserName(String name) {
		User user= null;
		ArrayList<User> userlist=(ArrayList<User>) userDAO.findByUsername(name);
		if (userlist.size() > 0){
			user = userlist.get(0);
		}

		return user;
	}
	
	public User getUserById(int id) {
		User user= userDAO.findById(id);
		return user;
	}

	public void setDocumentTypeDAO(DocumentTypeDAO documentTypeDAO) {
		this.documentTypeDAO = documentTypeDAO;
		System.out.println(">>>>>>>>>>>>documentTypeDAO");
	}

	public void setCommentPropertyDAO(CommentPropertyDAO commentPropertyDAO) {
		this.commentPropertyDAO = commentPropertyDAO;
		System.out.println(">>>>>>>>>>>>commentPropertyDAO");
	}

	public void setAttachmentDAO(AttachmentDAO attachmentDAO) {
		this.attachmentDAO = attachmentDAO;
		System.out.println(">>>>>>>>>>>>attachmentDAO");
	}

	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
		System.out.println(">>>>>>>>>>>>commentDAO");
	}

	public void setDocumentTypeHasDocumentPropertyTypeDAO(
			DocumentTypeHasDocumentPropertyTypeDAO documentTypeHasDocumentPropertyTypeDAO) {
		this.documentTypeHasDocumentPropertyTypeDAO = documentTypeHasDocumentPropertyTypeDAO;
		System.out.println(">>>>>>>>>>>>documentTypeHasDocumentPropertyTypeDAO");
	}

	public void setUserLogDAO(UserLogDAO userLogDAO) {
		this.userLogDAO = userLogDAO;
		System.out.println(">>>>>>>>>>>>userLogDAO");
	}

	public void setDocumentPropertyTypeDAO(
			DocumentPropertyTypeDAO documentPropertyTypeDAO) {
		this.documentPropertyTypeDAO = documentPropertyTypeDAO;
		System.out.println(">>>>>>>>>>>>documentPropertyTypeDAO");
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
		System.out.println(">>>>>>>>>>>>userDAO");
	}

	public void setTagDAO(TagDAO tagDAO) {
		this.tagDAO = tagDAO;
		System.out.println(">>>>>>>>>>>>tagDAO");
	}

	public void setReferenceDAO(ReferenceDAO referenceDAO) {
		this.referenceDAO = referenceDAO;
		System.out.println(">>>>>>>>>>>>referenceDAO");
	}

	public void setReferenceTypeDAO(ReferenceTypeDAO referenceTypeDAO) {
		this.referenceTypeDAO = referenceTypeDAO;
		System.out.println(">>>>>>>>>>>>referenceTypeDAO");
	}

	public void setDocumentDAO(DocumentDAO documentDAO) {
		this.documentDAO = documentDAO;
		System.out.println(">>>>>>>>>>>>documentDAO");
	}

	public void setCommentPropertyTypeDAO(
			CommentPropertyTypeDAO commentPropertyTypeDAO) {
		this.commentPropertyTypeDAO = commentPropertyTypeDAO;
		System.out.println(">>>>>>>>>>>>commentPropertyTypeDAO");
	}

	public void setTaggingDAO(TaggingDAO taggingDAO) {
		this.taggingDAO = taggingDAO;
		System.out.println(">>>>>>>>>>>>taggingDAO");
	}

	public void setDocumentHasDocumentPropertyDAO(
			DocumentHasDocumentPropertyDAO documentHasDocumentPropertyDAO) {
		this.documentHasDocumentPropertyDAO = documentHasDocumentPropertyDAO;
		System.out.println(">>>>>>>>>>>>documentHasDocumentPropertyDAO");
	}

	public void setDocumentPropertyDAO(DocumentPropertyDAO documentPropertyDAO) {
		this.documentPropertyDAO = documentPropertyDAO;
		System.out.println(">>>>>>>>>>>>documentPropertyDAO");
	}

}
