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
 * ReferenceType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "reference_type", catalog = "doc_hub")
public class ReferenceType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Set<Reference> references = new HashSet<Reference>(0);

	// Constructors

	/** default constructor */
	public ReferenceType() {
	}

	/** minimal constructor */
	public ReferenceType(String name) {
		this.name = name;
	}

	/** full constructor */
	public ReferenceType(String name, Set<Reference> references) {
		this.name = name;
		this.references = references;
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

	@Column(name = "name", nullable = false, length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "referenceType")
	public Set<Reference> getReferences() {
		return this.references;
	}

	public void setReferences(Set<Reference> references) {
		this.references = references;
	}

}