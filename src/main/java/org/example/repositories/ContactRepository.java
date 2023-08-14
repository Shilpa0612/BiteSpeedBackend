package org.example.repositories;

import org.example.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository <Contact, Integer> {
    @Query(value = "SELECT id FROM contact where phone = :phone || email = :email", nativeQuery = true)
    Integer getMatchedId(String email, String phone);

    Optional<Contact> findById (Integer id);

    Contact findByEmail(String email);

    Contact findByPhone(String phone);


}
