package cn.edu.nju.software.dochub.data.dataobject;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * Document entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "document", catalog = "doc_hub")
public class Document implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private DocumentType documentType;
	private String title;
	private String author;
	private Date year;
	private String pages;
	private String abstract_;
	private String keywords;
	private String publisher;
	private String url;
	private Date createdAt;
	private Date updatedAt;
	private boolean published;
	private Set<Tagging> taggings = new HashSet<Tagging>(0);
	private Set<Reference> referencesForDestination = new HashSet<Reference>(0);
	private Set<Reference> referencesForSource = new HashSet<Reference>(0);
	private Set<DocumentHasDocumentProperty> documentHasDocumentProperties = new HashSet<DocumentHasDocumentProperty>(
			0);
	private Set<Attachment> attachments = new HashSet<Attachment>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);

	// Constructors

	/** default constructor */
	public Document() {
	}

	/** minimal constructor */
	public Document(User user, DocumentType documentType, String title,
			String author, String abstract_, String keywords, String publisher) {
		this.user = user;
		this.documentType = documentType;
		this.title = title;
		this.author = author;
		this.abstract_ = abstract_;
		this.keywords = keywords;
		this.publisher = publisher;
	}

	/** full constructor */
	public Document(User user, DocumentType documentType, String title,
			String author, Date year, String pages, String abstract_,
			String keywords, String publisher, String url, Date createdAt,
			Date updatedAt, boolean published, Set<Tagging> taggings,
			Set<Reference> referencesForDestination,
			Set<Reference> referencesForSource,
			Set<DocumentHasDocumentProperty> documentHasDocumentProperties,
			Set<Attachment> attachments, Set<Comment> comments) {
		this.user = user;
		this.documentType = documentType;
		this.title = title;
		this.author = author;
		this.year = year;
		this.pages = pages;
		this.abstract_ = abstract_;
		this.keywords = keywords;
		this.publisher = publisher;
		this.url = url;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.published = published;
		this.taggings = taggings;
		this.referencesForDestination = referencesForDestination;
		this.referencesForSource = referencesForSource;
		this.documentHasDocumentProperties = documentHasDocumentProperties;
		this.attachments = attachments;
		this.comments = comments;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_type_id", nullable = false)
	public DocumentType getDocumentType() {
		return this.documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	@Column(name = "title", nullable = false, length = 256)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "author", nullable = false, length = 256)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "year", length = 0)
	public Date getYear() {
		return this.year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	@Column(name = "pages", length = 45)
	public String getPages() {
		return this.pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	@Column(name = "abstract", nullable = false, length = 65535)
	public String getAbstract_() {
		return this.abstract_;
	}

	public void setAbstract_(String abstract_) {
		this.abstract_ = abstract_;
	}

	@Column(name = "keywords", nullable = false, length = 64)
	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Column(name = "publisher", nullable = false, length = 128)
	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Column(name = "url", length = 256)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "created_at", length = 19)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "updated_at", length = 19)
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Column(name = "published")
	public boolean getPublished() {
		return this.published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "document")
	public Set<Tagging> getTaggings() {
		return this.taggings;
	}

	public void setTaggings(Set<Tagging> taggings) {
		this.taggings = taggings;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "documentByDestination")
	public Set<Reference> getReferencesForDestination() {
		return this.referencesForDestination;
	}

	public void setReferencesForDestination(
			Set<Reference> referencesForDestination) {
		this.referencesForDestination = referencesForDestination;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "documentBySource")
	public Set<Reference> getReferencesForSource() {
		return this.referencesForSource;
	}

	public void setReferencesForSource(Set<Reference> referencesForSource) {
		this.referencesForSource = referencesForSource;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "document")
	public Set<DocumentHasDocumentProperty> getDocumentHasDocumentProperties() {
		return this.documentHasDocumentProperties;
	}

	public void setDocumentHasDocumentProperties(
			Set<DocumentHasDocumentProperty> documentHasDocumentProperties) {
		this.documentHasDocumentProperties = documentHasDocumentProperties;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "document")
	public Set<Attachment> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "document")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

}