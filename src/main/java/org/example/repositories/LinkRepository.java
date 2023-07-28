package org.example.repositories;

import org.example.entities.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LinkRepository extends JpaRepository <Link, Integer>{

    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);

    @Query(value = "SELECT l from link l where l.linkedId = :id")
    List<Link> getAllLink(Integer id);
}
