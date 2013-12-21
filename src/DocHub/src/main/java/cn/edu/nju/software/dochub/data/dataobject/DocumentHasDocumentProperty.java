package cn.edu.nju.software.dochub.data.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * DocumentHasDocumentProperty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "document_has_document_property", catalog = "doc_hub")
public class DocumentHasDocumentProperty implements java.io.Serializable {

	// Fields

	private Integer id;
	private DocumentProperty documentProperty;
	private Document document;

	// Constructors

	/** default constructor */
	public DocumentHasDocumentProperty() {
	}

	/** full constructor */
	public DocumentHasDocumentProperty(DocumentProperty documentProperty,
			Document document) {
		this.documentProperty = documentProperty;
		this.document = document;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "document_property_id", nullable = false)
	public DocumentProperty getDocumentProperty() {
		return this.documentProperty;
	}

	public void setDocumentProperty(DocumentProperty documentProperty) {
		this.documentProperty = documentProperty;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "document_id", nullable = false)
	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

}