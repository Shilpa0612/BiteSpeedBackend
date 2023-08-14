package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.ContactDTO;
import org.example.entities.Contact;
import org.example.entities.Link;
import org.example.services.ContactService;
import org.example.services.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ContactLinkController {
    @Autowired
    private final ContactService contactService;
    @Autowired
    private final LinkService linkService;

    public ContactLinkController(ContactService contactService, LinkService linkService)
    {
        this.contactService = contactService;
        this.linkService = linkService;
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/identify")
    public ResponseEntity<String> mapContact(@RequestBody ContactDTO contactDTO) throws JsonProcessingException {
        String email = contactDTO.getEmail();
        String phone = contactDTO.getPhone();

        Contact existingContactByEmail = contactService.findByEmail(email);
        Contact existingContactByPhone = contactService.findByPhone(phone);



        if (existingContactByEmail != null) {
            Link linkObj;
            Integer linkedId = existingContactByEmail.getId();
            if (linkService.findByLinkedId(linkedId) != null) {
                linkObj = linkService.findByLinkedId(linkedId);

                linkObj.setPhone(phone);
                linkService.saveOrCreate(linkObj);
                Contact c = contactService.findByEmail(email);
                c.setLinkedId(linkObj.getId());
                contactService.saveOrCreate(c);
            } else {
                linkObj = new Link();

                linkObj.setLinkedId(linkedId);
                linkObj.setEmail(email);
                linkObj.setPhone(phone);
                linkObj.setLinkedPrecedence("secondary");
                linkObj.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
                linkObj.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
                linkService.saveOrCreate(linkObj);
                Contact c = contactService.findByEmail(email);
                c.setLinkedId(linkObj.getId());
                contactService.saveOrCreate(c);

            }

            List<Link> obj = linkService.getAllLink(linkedId);

            Map<String, Object> responseMap = new LinkedHashMap<>();
                responseMap.put("contact", Map.of("primaryContactId", linkObj.getId(),
                        "emails", email.split(" "),
                        "phoneNumbers", linkObj.getPhone().toString().split(" "),
                        "secondaryContactIds", linkObj.getLinkedId()));
                ObjectMapper mapper = new ObjectMapper();
                String jsonResponse = mapper.writeValueAsString(responseMap);
                return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

        }
        else if (existingContactByPhone != null) {
            //String temp;
            Integer linkedId = existingContactByPhone.getId();
            Link linkObj;
            if (linkService.findByLinkedId(linkedId) != null) {
                linkObj = linkService.findByLinkedId(linkedId);
                linkObj.setEmail(email);
                linkService.saveOrCreate(linkObj);
                Contact c = contactService.findByPhone(phone);
                c.setLinkedId(linkObj.getId());
              //  temp = phone;
                contactService.saveOrCreate(c);
            }
            else {
                linkObj = new Link();

                linkObj.setLinkedId(linkedId);
                linkObj.setEmail(email);
                linkObj.setPhone(phone);
                linkObj.setLinkedPrecedence("secondary");
                linkObj.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
                linkObj.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
                linkService.saveOrCreate(linkObj);
                Contact c = contactService.findByPhone(phone);
                c.setLinkedId(linkObj.getId());
            //    temp = phone;
                contactService.saveOrCreate(c);
            }
            //Link linkObj = linkService.findByPhone(phone);

            //Link linkObj = linkService.findByLinkedId(linkedId);
                Map<String, Object> responseMap = new LinkedHashMap<>();
                responseMap.put("contact", Map.of("primaryContactId", linkObj.getId(),
                        "emails", linkObj.getEmail().split(" "),
                        "phoneNumbers", linkObj.getPhone().split(" "),
                        "secondaryContactIds", linkObj.getLinkedId()));
                ObjectMapper mapper = new ObjectMapper();
                String jsonResponse = mapper.writeValueAsString(responseMap);
                return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

        }
        else  {
            Contact newContact = new Contact();
            newContact.setEmail(email);
            newContact.setPhone(phone);
            newContact.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
            newContact.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
            newContact.setLinkedPrecedence("primary");
            contactService.saveOrCreate(newContact);

            //Contact contactObj = contactService.findByEmail(email);
            //String val;
            // if(newContact.getLinkedId() == null)
            //    val ="[]";
            //else
            //  val = newContact.getLinkedId()+"";
            Contact obj = contactService.findByEmail(email);
            Map<String, Object> responseMap = new LinkedHashMap<>();
            responseMap.put("contact", Map.of("primaryContactId", obj.getId(),
                    "emails", newContact.getEmail(), "phoneNumbers", obj.getPhone(),
                    "secondaryContactIds", "null",
                    "linkPrecedence", obj.getLinkedPrecedence(),
                    "createdAt", obj.getCreatedAt(),
                    "updatedAt", obj.getUpdatedAt(),
                    "deletedAt", obj.getDeletedAt()));
            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse = mapper.writeValueAsString(responseMap);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }

       // return new ResponseEntity<>("Server Error", HttpStatus.NOT_FOUND);
    }

}
