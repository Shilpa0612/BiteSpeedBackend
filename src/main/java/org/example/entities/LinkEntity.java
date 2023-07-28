package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Data
public class LinkEntity {

    String linkedPrecedence;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;

    private List<String> email;
    private List<String> phone;
    private Integer linkedId;//Foreign Key
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
}
