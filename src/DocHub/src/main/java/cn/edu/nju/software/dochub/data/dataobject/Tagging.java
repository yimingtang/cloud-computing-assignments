package cn.edu.nju.software.dochub.data.dataobject;

import java.util.Date;
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
 * Tagging entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tagging", catalog = "doc_hub")
public class Tagging implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Tag tag;
	private Document document;
	private Date createdAt;
	private Date updatedAt;

	// Constructors

	/** default constructor */
	public Tagging() {
	}

	/** minimal constructor */
	public Tagging(User user, Tag tag, Document document, Date createdAt) {
		this.user = user;
		this.tag = tag;
		this.document = document;
		this.createdAt = createdAt;
	}

	/** full constructor */
	public Tagging(User user, Tag tag, Document document, Date createdAt,
			Date updatedAt) {
		this.user = user;
		this.tag = tag;
		this.document = document;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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
	@JoinColumn(name = "tagged_by", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tag_id", nullable = false)
	public Tag getTag() {
		return this.tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_id", nullable = false)
	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	@Column(name = "created_at", nullable = false, length = 19)
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

}