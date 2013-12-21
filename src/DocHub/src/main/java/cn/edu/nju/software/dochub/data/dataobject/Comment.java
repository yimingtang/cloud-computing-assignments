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
import org.hibernate.annotations.GenericGenerator;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "comment", catalog = "doc_hub")
public class Comment implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Document document;
	private String content;
	private short rank;
	private Date createdAt;
	private Date updatedAt;
	private boolean published;
	private Set<CommentProperty> commentProperties = new HashSet<CommentProperty>(
			0);

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** minimal constructor */
	public Comment(User user, Document document, String content) {
		this.user = user;
		this.document = document;
		this.content = content;
	}

	/** full constructor */
	public Comment(User user, Document document, String content, short rank,
			Date createdAt, Date updatedAt, boolean published,
			Set<CommentProperty> commentProperties) {
		this.user = user;
		this.document = document;
		this.content = content;
		this.rank = rank;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.published = published;
		this.commentProperties = commentProperties;
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
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "document_id", nullable = false)
	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "rank")
	public short getRank() {
		return this.rank;
	}

	public void setRank(short rank) {
		this.rank = rank;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "comment")
	public Set<CommentProperty> getCommentProperties() {
		return this.commentProperties;
	}

	public void setCommentProperties(Set<CommentProperty> commentProperties) {
		this.commentProperties = commentProperties;
	}

}