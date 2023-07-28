package org.example.repositories;

import org.example.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository <Contact, Integer> {
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
    @Query(value = "SELECT id FROM contact where phone = :phone || email = email", nativeQuery = true)
    public Integer getMatchedId(String email, String phone);

    //public boolean doesContactExist(String phone, String email);

   // @Query(value = "select c from contact c where c.id = :id")
   // public Contact getContactEntity(@Param("id")Integer id);

    Optional<Contact> findById (Integer id);
}
