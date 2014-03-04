package cn.edu.nju.software.dochub.data.dataobject;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Reference entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "reference", catalog = "doc_hub")
public class Reference implements java.io.Serializable {

    // Fields

    private Integer id;
    private ReferenceType referenceType;
    private Document documentBySource;
    private Document documentByDestination;

    // Constructors

    /**
     * default constructor
     */
    public Reference() {
    }

    /**
     * full constructor
     */
    public Reference(ReferenceType referenceType, Document documentBySource,
                     Document documentByDestination) {
        this.referenceType = referenceType;
        this.documentBySource = documentBySource;
        this.documentByDestination = documentByDestination;
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
    @JoinColumn(name = "reference_type_id", nullable = false)
    public ReferenceType getReferenceType() {
        return this.referenceType;
    }

    public void setReferenceType(ReferenceType referenceType) {
        this.referenceType = referenceType;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source", nullable = false)
    public Document getDocumentBySource() {
        return this.documentBySource;
    }

    public void setDocumentBySource(Document documentBySource) {
        this.documentBySource = documentBySource;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination", nullable = false)
    public Document getDocumentByDestination() {
        return this.documentByDestination;
    }

    public void setDocumentByDestination(Document documentByDestination) {
        this.documentByDestination = documentByDestination;
    }

}