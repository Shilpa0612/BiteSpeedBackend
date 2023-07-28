package org.example.repositories;

import org.example.entities.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository <LinkEntity, Integer>{

    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);

}
