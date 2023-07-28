package org.example.services;

import org.example.entities.LinkEntity;
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

    public LinkEntity saveOrCreate(LinkEntity linkEntity)
    {
        return linkRepository.save(linkEntity);
    }

    @Query(value ="Select * from link where id = :id")
    public List<LinkEntity> getAllLink(Integer id)
    {
        return linkRepository.findAll();
    }


}
