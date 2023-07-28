package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Data
public class LinkEntity {

    private String linkedPrecedence;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private List<String> email;
    private List<String> phone;

    private Integer linkedId;//Foreign Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private ContactEntity contact;

    public LinkEntity()
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
