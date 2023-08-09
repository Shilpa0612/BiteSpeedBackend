package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

//@AllArgsConstructor
@Entity
@Data
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;
    @Column(name = "email")
    String email;
    @Column(name = "phone")
    String phone;
    @Column(name = "linkedId")
    Integer linkedId;
    @Column(name = "linkedPrecedence")
    String linkedPrecedence;

    @OneToMany(mappedBy = "contactObj", cascade = CascadeType.ALL)
            private List<Link> ob;

    @Column(name = "createdAt")
    LocalDateTime createdAt;

    @Column(name = "updatedAt")
    LocalDateTime updatedAt;
    @Column(name = "deletedAt")
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }
}
