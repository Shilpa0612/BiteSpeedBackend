package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.ContactDTO;
import org.example.entities.ContactEntity;
import org.example.entities.LinkEntity;
import org.example.services.ContactService;
import org.example.services.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ContactLinkController {
    private final ContactService contactService;
    private final LinkService linkService;

    @Autowired
    public ContactLinkController(ContactService contactService, LinkService linkService)
    {
        this.contactService = contactService;
        this.linkService = linkService;
    }

  @PostMapping("/identify")
    public ResponseEntity<String> mapContact(@RequestBody ContactDTO contactDTO) throws JsonProcessingException {

        String email = contactDTO.getEmail();
        String phone = contactDTO.getPhone();

        boolean ifExists = contactService.returnIfIdExist(email, phone);

        if(ifExists)
        {
            LinkEntity linkInstance = new LinkEntity();
            Integer id = contactService.getIdFromMailPhone(email, phone);
            if(contactService.ifEmailExist(email)) {
                List <ContactEntity> listContact = contactService.getAllContacts();
                List<String> list = listContact.stream()
                                .map(contactEntity ->  contactEntity.getPhone()).collect(Collectors.toList());
                linkInstance.setPhone(list);
            }
            if(contactService.ifPhoneExist(phone))
            {
                List <ContactEntity> listContact = contactService.getAllContacts();
                List<String> list = listContact.stream()
                        .map(contactEntity ->  contactEntity.getEmail()).collect(Collectors.toList());
                linkInstance.setPhone(list);
            }

                ContactEntity contactEntity = contactService.getMatchedContact(id);

                linkInstance.setLinkedId(id);
               // linkInstance.setEmail(email);
               // linkInstance.setPhone(phone);
                linkInstance.setCreatedAt(LocalDateTime.now());
                linkInstance.setUpdatedAt(LocalDateTime.now());

                linkService.saveOrCreate(linkInstance);
        }
        else {

            ContactEntity contactInstance = new ContactEntity();
            contactInstance.setPhone(phone);
            contactInstance.setCreatedAt(LocalDateTime.now());
            contactInstance.setUpdatedAt(LocalDateTime.now());
            contactInstance.setEmail(email);


            contactService.saveOrUpdate(contactInstance);
        }



    //public List<LinkEntity> getAllContactsWithLink() throws JsonProcessingException {

        List<ContactEntity> contactEntities = contactService.getAllContacts();
        ObjectMapper mapper = new ObjectMapper();
        String response = "";

        for(ContactEntity c: contactEntities)
        {
            List<LinkEntity> linkEntities = linkService.getAllLink(c.getId());
            response += mapper.writeValueAsString(c);
            response += mapper.writeValueAsString(linkEntities);

        }
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));

    }

}
