package org.example.repositories;

import org.example.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository <ContactEntity, Integer> {
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
    @Query(value = "SELECT id FROM contact where phone = :phone || email = :email")
    public Integer getMatchedId(String email, String phone);
    public boolean doesContactExist(String phone, String email);

    @Query(value = "select * from contact where id = :id")
    public ContactEntity getContactEntity(Integer id);
}
