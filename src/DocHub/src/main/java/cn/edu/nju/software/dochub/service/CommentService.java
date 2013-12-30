package cn.edu.nju.software.dochub.service;

import java.util.List;

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
import cn.edu.nju.software.dochub.data.dataobject.Comment;
import cn.edu.nju.software.dochub.data.dataobject.CommentProperty;
import cn.edu.nju.software.dochub.data.dataobject.CommentPropertyType;

public class CommentService {
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
	
	public List<Comment> getAllComment(){
		return commentDAO.findAll();
	}
	
	public void addSimpleComment(Comment comment ){
		commentDAO.save(comment);
	}
	
	public List<Comment> findAllPublishedCommentByDocId(int docid){
		return commentDAO.findAllPublishedByDocId(docid);
	}
	
	public List<CommentPropertyType> getAllCommentPropertyTypes(){
		return commentPropertyTypeDAO.findAll();
	}
	
	public void deleteCommentPropertyType(int id){
		commentPropertyTypeDAO.delete(commentPropertyTypeDAO.findById(id));
	}
	
	public void addCommentPropertyType(CommentPropertyType commentpropertytype){
		commentPropertyTypeDAO.save(commentpropertytype);
	}
	

	public void addCommentProperty(CommentProperty cp) {
		// TODO Auto-generated method stub
		commentPropertyDAO.save(cp);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void setDocumentTypeDAO(DocumentTypeDAO documentTypeDAO) {
		this.documentTypeDAO = documentTypeDAO;
	}
	public void setCommentPropertyDAO(CommentPropertyDAO commentPropertyDAO) {
		this.commentPropertyDAO = commentPropertyDAO;
	}
	public void setAttachmentDAO(AttachmentDAO attachmentDAO) {
		this.attachmentDAO = attachmentDAO;
	}
	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}
	public void setDocumentTypeHasDocumentPropertyTypeDAO(
			DocumentTypeHasDocumentPropertyTypeDAO documentTypeHasDocumentPropertyTypeDAO) {
		this.documentTypeHasDocumentPropertyTypeDAO = documentTypeHasDocumentPropertyTypeDAO;
	}
	public void setUserLogDAO(UserLogDAO userLogDAO) {
		this.userLogDAO = userLogDAO;
	}
	public void setDocumentPropertyTypeDAO(
			DocumentPropertyTypeDAO documentPropertyTypeDAO) {
		this.documentPropertyTypeDAO = documentPropertyTypeDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public void setTagDAO(TagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}
	public void setReferenceDAO(ReferenceDAO referenceDAO) {
		this.referenceDAO = referenceDAO;
	}
	public void setReferenceTypeDAO(ReferenceTypeDAO referenceTypeDAO) {
		this.referenceTypeDAO = referenceTypeDAO;
	}
	public void setDocumentDAO(DocumentDAO documentDAO) {
		this.documentDAO = documentDAO;
	}
	public void setCommentPropertyTypeDAO(
			CommentPropertyTypeDAO commentPropertyTypeDAO) {
		this.commentPropertyTypeDAO = commentPropertyTypeDAO;
	}
	public void setTaggingDAO(TaggingDAO taggingDAO) {
		this.taggingDAO = taggingDAO;
	}
	public void setDocumentHasDocumentPropertyDAO(
			DocumentHasDocumentPropertyDAO documentHasDocumentPropertyDAO) {
		this.documentHasDocumentPropertyDAO = documentHasDocumentPropertyDAO;
	}
	public void setDocumentPropertyDAO(DocumentPropertyDAO documentPropertyDAO) {
		this.documentPropertyDAO = documentPropertyDAO;
	}

}
