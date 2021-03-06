package cn.edu.nju.software.dochub.data.dataobject;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * DocumentProperty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "document_property", catalog = "doc_hub")
public class DocumentProperty implements java.io.Serializable {

    // Fields

    private Integer id;
    private DocumentPropertyType documentPropertyType;
    private String value;
    private Set<DocumentHasDocumentProperty> documentHasDocumentProperties = new HashSet<DocumentHasDocumentProperty>(
            0);

    // Constructors

    /**
     * default constructor
     */
    public DocumentProperty() {
    }

    /**
     * minimal constructor
     */
    public DocumentProperty(DocumentPropertyType documentPropertyType) {
        this.documentPropertyType = documentPropertyType;
    }

    /**
     * full constructor
     */
    public DocumentProperty(DocumentPropertyType documentPropertyType,
                            String value,
                            Set<DocumentHasDocumentProperty> documentHasDocumentProperties) {
        this.documentPropertyType = documentPropertyType;
        this.value = value;
        this.documentHasDocumentProperties = documentHasDocumentProperties;
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
    @JoinColumn(name = "document_property_type_id", nullable = false)
    public DocumentPropertyType getDocumentPropertyType() {
        return this.documentPropertyType;
    }

    public void setDocumentPropertyType(
            DocumentPropertyType documentPropertyType) {
        this.documentPropertyType = documentPropertyType;
    }

    @Column(name = "value", length = 64)
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "documentProperty")
    public Set<DocumentHasDocumentProperty> getDocumentHasDocumentProperties() {
        return this.documentHasDocumentProperties;
    }

    public void setDocumentHasDocumentProperties(
            Set<DocumentHasDocumentProperty> documentHasDocumentProperties) {
        this.documentHasDocumentProperties = documentHasDocumentProperties;
    }

}