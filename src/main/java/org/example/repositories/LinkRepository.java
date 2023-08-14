package org.example.repositories;

import org.example.entities.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository <Link, Integer>{

    Link findByLinkedId(Integer linkedId);
    Link findByEmail(String email);
    Link findByPhone(String phone);
    Boolean existsByLinkedId(Integer linkedId);

    List<Link> findAllByLinkedId(Integer linkedId);
    //List<Link> findByLinkedIdIn(List<Integer> contactIds);
}
