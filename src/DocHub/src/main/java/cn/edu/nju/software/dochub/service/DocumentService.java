package cn.edu.nju.software.dochub.service;

import cn.edu.nju.software.dochub.data.dao.*;
import cn.edu.nju.software.dochub.data.dataobject.Document;
import cn.edu.nju.software.dochub.data.dataobject.DocumentType;

import java.io.File;
import java.util.Date;
import java.util.List;

public class DocumentService {
    public static String absoluteAttachmentDir = System.getProperty("project.root") + "resources" + File.separator + "attachments" + File.separator;
    public static String relativeAttachmentDir = "resources" + File.separator + "attachments" + File.separator;
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

    public List<DocumentType> getAllDocumentType() {
        return documentTypeDAO.findAll();
    }

    public void addDocument(Document document) {
        documentDAO.save(document);
    }

    public DocumentType findDocTypeByName(String name) {
        List<DocumentType> doclist = documentTypeDAO.findByTypeName(name);
        if (doclist.size() > 0) {
            return doclist.get(0);
        }
        return null;
    }

    public List<Document> getAllDocument() {
        return documentDAO.findAll();
    }

    public Document findDocumentById(int id) {
        Document doc = documentDAO.findById(id);
        return doc;
    }

    public void updateDocument(Document document) {
        documentDAO.merge(document);
    }

    public List<Document> findDocByFuzzy(String word) {
        return documentDAO.findLikeWord(word);
    }

    public List<Document> findDocByAccutate(String title, String author,
                                            Date yearFrom, Date yearTo, String abstract_, String keyword,
                                            String publisher, String url, DocumentType documenttype) {
        return documentDAO.findLikeWords(title, author, yearFrom, yearTo,
                abstract_, keyword, publisher, url, documenttype);
    }

    public List<Document> getAllDocumentByUserId(int userId) {
        // TODO Auto-generated method stub

        return documentDAO.findByUserId(userId);
    }

    public List<Document> getAllUserCommentedDocumentByUserId(int userId) {
        // TODO Auto-generated method stub
        return documentDAO.findAllUserCommentedDocument(userId);
    }

    public void deleteDocument(int docid) {
        // TODO Auto-generated method stub
        documentDAO.delete(documentDAO.findById(docid));
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
