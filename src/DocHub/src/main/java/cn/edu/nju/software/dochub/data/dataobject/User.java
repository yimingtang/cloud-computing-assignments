package cn.edu.nju.software.dochub.data.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user")
public class User implements java.io.Serializable {

	// Fields

	private Integer iduser;
	private String username;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String username) {
		this.username = username;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "iduser", unique = true, nullable = false)
	public Integer getIduser() {
		return this.iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}

	@Column(name = "username", length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}