package org.example.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//@AllArgsConstructor
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
    @Column(name = "linkedId")
    Integer linkedId;
    @Column(name = "linkedPrecedence")
    String linkedPrecedence;

    @OneToMany(mappedBy = "contactObj", cascade = CascadeType.ALL)
            private List<Link> ob;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @Column(name = "createdAt")
    LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @Column(name = "updatedAt")
    LocalDateTime updatedAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
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

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", linkedId=" + linkedId +
                ", linkedPrecedence='" + linkedPrecedence + '\'' +
                ", ob=" + ob.stream().map(Link::toString).collect(Collectors.joining(", ")) +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }

}
