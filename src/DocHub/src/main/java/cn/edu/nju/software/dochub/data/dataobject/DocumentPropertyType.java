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
 * DocumentPropertyType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "document_property_type", catalog = "doc_hub")
public class DocumentPropertyType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Set<DocumentProperty> documentProperties = new HashSet<DocumentProperty>(
			0);
	private Set<DocumentTypeHasDocumentPropertyType> documentTypeHasDocumentPropertyTypes = new HashSet<DocumentTypeHasDocumentPropertyType>(
			0);

	// Constructors

	/** default constructor */
	public DocumentPropertyType() {
	}

	/** minimal constructor */
	public DocumentPropertyType(String name) {
		this.name = name;
	}

	/** full constructor */
	public DocumentPropertyType(
			String name,
			Set<DocumentProperty> documentProperties,
			Set<DocumentTypeHasDocumentPropertyType> documentTypeHasDocumentPropertyTypes) {
		this.name = name;
		this.documentProperties = documentProperties;
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

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "documentPropertyType")
	public Set<DocumentProperty> getDocumentProperties() {
		return this.documentProperties;
	}

	public void setDocumentProperties(Set<DocumentProperty> documentProperties) {
		this.documentProperties = documentProperties;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "documentPropertyType")
	public Set<DocumentTypeHasDocumentPropertyType> getDocumentTypeHasDocumentPropertyTypes() {
		return this.documentTypeHasDocumentPropertyTypes;
	}

	public void setDocumentTypeHasDocumentPropertyTypes(
			Set<DocumentTypeHasDocumentPropertyType> documentTypeHasDocumentPropertyTypes) {
		this.documentTypeHasDocumentPropertyTypes = documentTypeHasDocumentPropertyTypes;
	}

}