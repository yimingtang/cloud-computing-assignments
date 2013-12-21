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
 * CommentProperty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "comment_property", catalog = "doc_hub")
public class CommentProperty implements java.io.Serializable {

	// Fields

	private Integer id;
	private Comment comment;
	private CommentPropertyType commentPropertyType;
	private String value;

	// Constructors

	/** default constructor */
	public CommentProperty() {
	}

	/** minimal constructor */
	public CommentProperty(Comment comment,
			CommentPropertyType commentPropertyType) {
		this.comment = comment;
		this.commentPropertyType = commentPropertyType;
	}

	/** full constructor */
	public CommentProperty(Comment comment,
			CommentPropertyType commentPropertyType, String value) {
		this.comment = comment;
		this.commentPropertyType = commentPropertyType;
		this.value = value;
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
	@JoinColumn(name = "comment_id", nullable = false)
	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "comment_property_type_id", nullable = false)
	public CommentPropertyType getCommentPropertyType() {
		return this.commentPropertyType;
	}

	public void setCommentPropertyType(CommentPropertyType commentPropertyType) {
		this.commentPropertyType = commentPropertyType;
	}

	@Column(name = "value", length = 65535)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}