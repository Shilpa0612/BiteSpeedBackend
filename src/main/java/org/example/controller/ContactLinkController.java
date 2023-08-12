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
        Contact newContact = new Contact();
        newContact.setEmail(email);
        newContact.setPhone(phone);
        newContact.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        newContact.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        if (existingContactByEmail != null) {
            Link link = new Link();
            link.setLinkedId(existingContactByEmail.getId());
            link.setEmail(email);
            link.setPhone(phone);
            link.setLinkedPrecedence("secondary");
            link.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
            link.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
            linkService.saveOrCreate(link);
        }
        if (existingContactByPhone != null) {
            Link link = new Link();
            link.setLinkedId(existingContactByPhone.getId());
            link.setEmail(email);
            link.setPhone(phone);
            link.setLinkedPrecedence("secondary");
            link.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
            link.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
            linkService.saveOrCreate(link);
        }
        if (existingContactByEmail == null && existingContactByPhone == null) {
            contactService.saveOrCreate(newContact);
        }
        Contact primaryContact = (existingContactByEmail != null) ? existingContactByEmail : contactService.findByPhone(phone);
        List<Link> linkEntities = linkService.getAllLink(primaryContact.getId());
        List<String> emails = new ArrayList<>();
        List<String> phones = new ArrayList<>();
        List<Integer> secondaryContactIds = new ArrayList<>();
        emails.add(primaryContact.getEmail().toString());
        phones.add(primaryContact.getPhone().toString());
        for (Link link : linkEntities) {
            if (link.getLinkedPrecedence().equalsIgnoreCase("secondary")) {
                secondaryContactIds.add(link.getLinkedId());
                emails.add(link.getEmail().toString());
                phones.add(link.getPhone().toString());
            }
        }
        Map<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("contact", Map.of("primaryContactId", primaryContact.getId(),
                "emails", emails, "phoneNumbers", phones, "secondaryContactIds", secondaryContactIds));
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(responseMap);
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

}
