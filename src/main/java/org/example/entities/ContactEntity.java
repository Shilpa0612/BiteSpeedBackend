package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@Entity
@Data
@Table(name = "contact")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
   // Integer secondary;
    String email;
    String phone;
    Integer LinkedId;
    String linkedPrecedence;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;

    public ContactEntity ()
    {
        deletedAt = null;
        linkedPrecedence = "primary";
        LinkedId = null;
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
}
