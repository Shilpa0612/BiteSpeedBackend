package org.example.repositories;

import org.example.entities.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository <Link, Integer>{

    List<Link> findByLinkedId(Integer linkedId);

    List<Link> findByLinkedIdIn(List<Integer> contactIds);
}
