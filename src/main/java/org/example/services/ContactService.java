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

    public boolean returnIfIdExist(String phone, String email)
    {
        if ((email != null && !email.isEmpty()) || (phone != null && !phone.isEmpty()))
            return contactRepository.existsByEmail(email) || contactRepository.existsByPhone(phone);
        //return contactRepository.existsByEmail(email) || contactRepository.existsByPhone(phone);
        /*if (email != null && !email.isEmpty()) {
            return contactRepository.existsByEmail(email);
        }
        if (phone != null && !phone.isEmpty()) {
            return contactRepository.existsByPhone(phone);
        }*/
        return false;
    }

    public Integer getIdFromMailPhone(String email, String phone)
    {
        return contactRepository.getMatchedId(email, phone);
    }
    @Modifying
    @Transactional
    public Contact saveOrCreate(Contact entity)
    {
        return contactRepository.save(entity);
    }

    public List<Contact> getAllContacts()
    {
        return contactRepository.findAll();
    }

    public Contact getMatchedContact(Integer id)
    {
        return contactRepository.findById(id).orElse(null);
    }

    public boolean ifEmailExist(String email)
    {
        return contactRepository.existsByEmail(email);
    }

    public boolean ifPhoneExist(String phone)
    {
        return contactRepository.existsByPhone(phone);
    }
}
