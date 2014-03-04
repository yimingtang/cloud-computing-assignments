package cn.edu.nju.software.dochub.data.dataobject;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * DocumentTypeHasDocumentPropertyType entity. @author MyEclipse Persistence
 * Tools
 */
@Entity
@Table(name = "document_type_has_document_property_type", catalog = "doc_hub")
public class DocumentTypeHasDocumentPropertyType implements
        java.io.Serializable {

    // Fields

    private Integer id;
    private DocumentType documentType;
    private DocumentPropertyType documentPropertyType;

    // Constructors

    /**
     * default constructor
     */
    public DocumentTypeHasDocumentPropertyType() {
    }

    /**
     * full constructor
     */
    public DocumentTypeHasDocumentPropertyType(DocumentType documentType,
                                               DocumentPropertyType documentPropertyType) {
        this.documentType = documentType;
        this.documentPropertyType = documentPropertyType;
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
    @JoinColumn(name = "document_type_id", nullable = false)
    public DocumentType getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
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

}