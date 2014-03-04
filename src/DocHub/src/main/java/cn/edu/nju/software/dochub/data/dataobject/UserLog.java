package cn.edu.nju.software.dochub.data.dataobject;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * UserLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_log", catalog = "doc_hub")
public class UserLog implements java.io.Serializable {

    // Fields

    private Integer id;
    private User user;
    private String name;
    private short type;
    private String description;
    private Date createdAt;

    // Constructors

    /**
     * default constructor
     */
    public UserLog() {
    }

    /**
     * minimal constructor
     */
    public UserLog(User user, String name, short type) {
        this.user = user;
        this.name = name;
        this.type = type;
    }

    /**
     * full constructor
     */
    public UserLog(User user, String name, short type, String description,
                   Date createdAt) {
        this.user = user;
        this.name = name;
        this.type = type;
        this.description = description;
        this.createdAt = createdAt;
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
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type", nullable = false)
    public short getType() {
        return this.type;
    }

    public void setType(short type) {
        this.type = type;
    }

    @Column(name = "description", length = 45)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "created_at", length = 19)
    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}