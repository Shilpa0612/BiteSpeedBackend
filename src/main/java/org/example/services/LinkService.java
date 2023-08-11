package org.example.services;

import org.example.entities.Link;
import org.example.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class LinkService {
    @Autowired
    private LinkRepository linkRepository;

    public LinkService (LinkRepository linkRepository)
    {
        this.linkRepository = linkRepository;
    }

    @Modifying
    @Transactional
    public Link saveOrCreate(Link linkEntity)
    {
        return linkRepository.save(linkEntity);
    }

    @Query(value ="Select * from link where id = :id", nativeQuery = true)
    public List<Link> getAllLink(Integer id)
    {
        return linkRepository.findByLinkedId(id);
    }

}
