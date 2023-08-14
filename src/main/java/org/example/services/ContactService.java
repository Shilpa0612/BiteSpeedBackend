package org.example.services;

import org.example.entities.Contact;
import org.example.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContactService {
    @Autowired
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository)
    {
        this.contactRepository = contactRepository;
    }

    public Contact findByEmail(String email) {
            return contactRepository.findByEmail(email);
    }

    public Contact findByPhone(String phone){
        return contactRepository.findByPhone(phone);
    }

    @Modifying
    @Transactional
    public Contact saveOrCreate(Contact entity)
    {
        return contactRepository.save(entity);
    }



}
