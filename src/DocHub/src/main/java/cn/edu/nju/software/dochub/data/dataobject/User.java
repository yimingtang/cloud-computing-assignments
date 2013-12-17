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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "doc_hub", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String password;
	private String salt;
	private String name;
	private String email;
	private short permissionLevel;
	private Date createdAt;
	private Date updatedAt;
	private boolean active;
	private Set<UserLog> userLogs = new HashSet<UserLog>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<Tagging> taggings = new HashSet<Tagging>(0);
	private Set<Document> documents = new HashSet<Document>(0);
	private Set<Attachment> attachments = new HashSet<Attachment>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String password, String salt, String name,
			short permissionLevel, Date createdAt, boolean active) {
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.name = name;
		this.permissionLevel = permissionLevel;
		this.createdAt = createdAt;
		this.active = active;
	}

	/** full constructor */
	public User(String username, String password, String salt, String name,
			String email, short permissionLevel, Date createdAt,
			Date updatedAt, boolean active, Set<UserLog> userLogs,
			Set<Comment> comments, Set<Tagging> taggings,
			Set<Document> documents, Set<Attachment> attachments) {
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.name = name;
		this.email = email;
		this.permissionLevel = permissionLevel;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.active = active;
		this.userLogs = userLogs;
		this.comments = comments;
		this.taggings = taggings;
		this.documents = documents;
		this.attachments = attachments;
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

	@Column(name = "username", unique = true, nullable = false, length = 32)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "salt", nullable = false, length = 12)
	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email", length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "permission_level", nullable = false)
	public short getPermissionLevel() {
		return this.permissionLevel;
	}

	public void setPermissionLevel(short permissionLevel) {
		this.permissionLevel = permissionLevel;
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

	@Column(name = "active", nullable = false)
	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserLog> getUserLogs() {
		return this.userLogs;
	}

	public void setUserLogs(Set<UserLog> userLogs) {
		this.userLogs = userLogs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Tagging> getTaggings() {
		return this.taggings;
	}

	public void setTaggings(Set<Tagging> taggings) {
		this.taggings = taggings;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Document> getDocuments() {
		return this.documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Attachment> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

}