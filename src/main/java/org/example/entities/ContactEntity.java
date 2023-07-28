package org.example.entities;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@Entity
@Data
public class ContactEntity {
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

    /*boolean checkIfexist(Integer primary, String email, String phone)
    {
        return false;
    }
    public String getEmail() {
        return email;
    }

    public List<String> getPhone() {
        return phone;
    }

    public String getPrimaryContact()
    {
        return email.get(0);
    }*/
}
