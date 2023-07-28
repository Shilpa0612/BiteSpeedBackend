package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table (name = "link")
public class Link {

    private String linkedPrecedence;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @ElementCollection
    private List<String> email;
    @ElementCollection
    private List<String> phone;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "linkedId", referencedColumnName = "id")
    private Contact contact;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "linkedId", updatable = false,nullable = false)
    private Integer linkedId;//Foreign Key
    public Link()
    {
        email = new ArrayList<>();
        phone = new ArrayList<>();
        linkedPrecedence = "secondary";
        deletedAt = null;
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

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public List<String> getEmail() {
        return email;
    }

    public List<String> getPhone() {
        return phone;
    }
}
