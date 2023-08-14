package org.example.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.id.factory.internal.AutoGenerationTypeStrategy;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "contact")
public class Contact{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;
    @Column(name = "email")
    String email;
    @Column(name = "phone")
    String phone;
    @Column(name = "linked_id")
    Integer linkedId;
    @Column(name = "linked_precedence")
    String linkedPrecedence;

    @OneToMany(mappedBy = "contactObj", cascade = CascadeType.ALL)
            private List<Link> ob;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @Column(name = "deleted_at")
    LocalDateTime deletedAt;

    public Contact ()
    {
        deletedAt = null;
        linkedPrecedence = "primary";
        linkedId = null;
    }


    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
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

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public String getLinkedPrecedence() {
        return linkedPrecedence;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
