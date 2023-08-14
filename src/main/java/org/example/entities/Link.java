package org.example.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table (name = "link")
public class Link{

    @Column(name = "linked_precedence")
    private String linkedPrecedence;
    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime deletedAt;

    //@ElementCollection
    @Column(name = "email", columnDefinition = "TEXT")
    private String email;
    //@ElementCollection
    @Column(name = "phone", columnDefinition = "TEXT")
    private String phone;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "linked_id", updatable = false, insertable = false)
    private Integer linkedId;//Foreign Key

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "linked_id", referencedColumnName = "id")
    @JsonIgnore
    private Contact contactObj;


    public Link()
    {
        //email = new ArrayList<>();
        //phone = new ArrayList<>();
        linkedPrecedence = "secondary";
        deletedAt = null;
        linkedId=-1;
    }

    public void setLinkedId(Integer linkedId) {
        this.linkedId = linkedId;
    }

    public void setLinkedPrecedence(String linkedPrecedence) {
        this.linkedPrecedence = linkedPrecedence;
    }


    public Integer getLinkedId() {
        return linkedId;
    }

    public void setCreatedAt(LocalDateTime obj)
    {
        this.createdAt = obj;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLinkedPrecedence() {
        return linkedPrecedence;
    }

    public void setEmail(String email) {
        StringBuffer s = new StringBuffer(this.email+" "+email);
        this.email = s.toString();
    }

    public void setPhone(String phone) {
        StringBuffer s = new StringBuffer(this.phone+" "+phone);
        this.phone = s.toString();
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getId() {
        return id;
    }
}
