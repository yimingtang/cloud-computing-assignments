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
 * Attachment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "attachment", catalog = "doc_hub")
public class Attachment implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Document document;
	private String name;
	private String url;
	private short type;
	private Date createdAt;
	private Date updatedAt;

	// Constructors

	/** default constructor */
	public Attachment() {
	}

	/** minimal constructor */
	public Attachment(User user, Document document, String name, String url,
			short type, Date createdAt) {
		this.user = user;
		this.document = document;
		this.name = name;
		this.url = url;
		this.type = type;
		this.createdAt = createdAt;
	}

	/** full constructor */
	public Attachment(User user, Document document, String name, String url,
			short type, Date createdAt, Date updatedAt) {
		this.user = user;
		this.document = document;
		this.name = name;
		this.url = url;
		this.type = type;
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
	@JoinColumn(name = "created_by", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_id", nullable = false)
	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	@Column(name = "name", nullable = false, length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "url", nullable = false, length = 256)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "type", nullable = false)
	public short getType() {
		return this.type;
	}

	public void setType(short type) {
		this.type = type;
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