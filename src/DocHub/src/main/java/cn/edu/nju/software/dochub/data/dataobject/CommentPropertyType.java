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
 * CommentPropertyType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "comment_property_type", catalog = "doc_hub")
public class CommentPropertyType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Set<CommentProperty> commentProperties = new HashSet<CommentProperty>(
			0);

	// Constructors

	/** default constructor */
	public CommentPropertyType() {
	}

	/** minimal constructor */
	public CommentPropertyType(String name) {
		this.name = name;
	}

	/** full constructor */
	public CommentPropertyType(String name,
			Set<CommentProperty> commentProperties) {
		this.name = name;
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

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commentPropertyType")
	public Set<CommentProperty> getCommentProperties() {
		return this.commentProperties;
	}

	public void setCommentProperties(Set<CommentProperty> commentProperties) {
		this.commentProperties = commentProperties;
	}

}