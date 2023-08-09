package org.example.repositories;

import org.example.entities.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository <Link, Integer>{

    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);

   // @Query(value = "SELECT l from link l, contact c where l.linkedId = :id and l.LinkedId = c.id")
    //List<Link> getAllLink(Integer id);

 //   @Query(value = "SELECT l from link l, contact c where l.LinkedId = :linkedId and l.LinkedId = c.id")
    List<Link> findByLinkedId(Integer linkedId);
}
