package org.example.services;

import org.example.entities.Link;
import org.example.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkService {
    private LinkRepository linkRepository;

    @Autowired
    public LinkService (LinkRepository linkRepository)
    {
        this.linkRepository = linkRepository;
    }

    public Link saveOrCreate(Link linkEntity)
    {
        return linkRepository.save(linkEntity);
    }

    @Query(value ="Select * from link where id = :id")
    public List<Link> getAllLink(Integer id)
    {
        return linkRepository.findByLinkedId(id);
    }


}
