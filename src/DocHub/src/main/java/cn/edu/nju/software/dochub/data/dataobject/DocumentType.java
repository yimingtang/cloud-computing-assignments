package cn.edu.nju.software.dochub.data.dataobject;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * DocumentType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "document_type", catalog = "doc_hub")
public class DocumentType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String typeName;
	private Set<Document> documents = new HashSet<Document>(0);
	private Set<DocumentTypeHasDocumentPropertyType> documentTypeHasDocumentPropertyTypes = new HashSet<DocumentTypeHasDocumentPropertyType>(
			0);

	// Constructors

	/** default constructor */
	public DocumentType() {
	}

	/** minimal constructor */
	public DocumentType(String typeName) {
		this.typeName = typeName;
	}

	/** full constructor */
	public DocumentType(
			String typeName,
			Set<Document> documents,
			Set<DocumentTypeHasDocumentPropertyType> documentTypeHasDocumentPropertyTypes) {
		this.typeName = typeName;
		this.documents = documents;
		this.documentTypeHasDocumentPropertyTypes = documentTypeHasDocumentPropertyTypes;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "type_name", nullable = false, length = 45)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "documentType")
	public Set<Document> getDocuments() {
		return this.documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "documentType")
	public Set<DocumentTypeHasDocumentPropertyType> getDocumentTypeHasDocumentPropertyTypes() {
		return this.documentTypeHasDocumentPropertyTypes;
	}

	public void setDocumentTypeHasDocumentPropertyTypes(
			Set<DocumentTypeHasDocumentPropertyType> documentTypeHasDocumentPropertyTypes) {
		this.documentTypeHasDocumentPropertyTypes = documentTypeHasDocumentPropertyTypes;
	}

}