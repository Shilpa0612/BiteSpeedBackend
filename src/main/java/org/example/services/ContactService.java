package org.example.services;

import org.example.entities.Contact;
import org.example.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository)
    {
        this.contactRepository = contactRepository;
    }

    /*@Query(value = "SELECT id FROM contact where phone = :phone || email = :email")
    public Integer getIdByEmailPhone(String phone, String email)
    {
        return contactRepository.get(phone) || contactRepository.existsByEmail(email);
    }*/

    public boolean returnIfIdExist(String phone, String email)
    {
        return contactRepository.existsByEmail(email) || contactRepository.existsByPhone(phone);
    }

    public Integer getIdFromMailPhone(String email, String phone)
    {
        return contactRepository.getMatchedId(email, phone);
    }
    public Contact saveOrUpdate(Contact entity)
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
