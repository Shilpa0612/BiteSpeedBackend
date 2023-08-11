package org.example.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Table (name = "link")
public class Link{

    private String linkedPrecedence;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime updatedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime deletedAt;

    @ElementCollection
    @Column(name = "email", columnDefinition = "TEXT")
    private List<String> email;
    @ElementCollection
    @Column(name = "phone", columnDefinition = "TEXT")
    private List<String> phone;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "LinkedId", updatable = false, insertable = false)
    private Integer linkedId;//Foreign Key

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LinkedId", referencedColumnName = "id")
    @JsonIgnore
    private Contact contactObj;


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

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", linkedId=" + linkedId +
                ", createdAt=" + createdAt +
                ", deletedAt=" + deletedAt +
                ", linkedPrecedence='" + linkedPrecedence + '\'' +
                ", updatedAt=" + updatedAt +
                ", email=" + email.stream().collect(Collectors.joining(", ")) +
                ", phone=" + phone.stream().collect(Collectors.joining(", ")) +
                '}';
    }

}
