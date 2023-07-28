package org.example.services;

import org.example.entities.ContactEntity;
import org.example.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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
        return contactRepository.doesContactExist(phone, email);
    }

    public Integer getIdFromMailPhone(String email, String phone)
    {
        return contactRepository.getMatchedId(email, phone);
    }
    public ContactEntity saveOrUpdate(ContactEntity entity)
    {
        return contactRepository.save(entity);
    }

    public List<ContactEntity> getAllContacts()
    {
        return contactRepository.findAll();
    }

    public ContactEntity getMatchedContact(Integer id)
    {
        return contactRepository.getContactEntity(id);
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
