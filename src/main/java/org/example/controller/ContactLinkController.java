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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;
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

      if (ifExists) {
          Integer id = contactService.getIdFromMailPhone(email, phone);
          Contact contactEntity = contactService.getMatchedContact(id);

          Link linkInstance = new Link();
          linkInstance.setLinkedId(id);
          linkInstance.setCreatedAt(LocalDateTime.now());
          linkInstance.setUpdatedAt(LocalDateTime.now());

          List<Link> linkList = linkService.getAllLink(id);
          if (contactService.ifEmailExist(email)) {
              List<String> emailList = linkList.stream()
                      .map(Link::getEmail)
                      .flatMap(List::stream)
                      .collect(Collectors.toList());
              if (!emailList.contains(email)) {
                  emailList.add(email);
                  linkInstance.setEmail(emailList);
              }
          }

          if (contactService.ifPhoneExist(phone)) {
              List<String> phoneList = linkList.stream()
                      .map(Link::getPhone)
                      .flatMap(List::stream)
                      .collect(Collectors.toList());
              if (!phoneList.contains(email)) {
                  phoneList.add(email);
                  linkInstance.setEmail(phoneList);
              }
          }

          linkService.saveOrCreate(linkInstance);
      } else {

          Contact contactInstance = new Contact();
          contactInstance.setPhone(phone);
          contactInstance.setCreatedAt(LocalDateTime.now());
          contactInstance.setUpdatedAt(LocalDateTime.now());
          contactInstance.setEmail(email);


          contactService.saveOrUpdate(contactInstance);
      }

    //public List<LinkEntity> getAllContactsWithLink() throws JsonProcessingException {

        List<Contact> contactEntities = contactService.getAllContacts();

        List<String> response = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        for(Contact c: contactEntities)
        {
            List<Link> linkEntities = linkService.getAllLink(c.getId());
            String read = createResponse(c, linkEntities);
            response.add(read);
        }
        String jsonResponse = String.join(",", response);
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

    private String createResponse(Contact contact, List<Link> list) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String primaryId = String.valueOf(contact.getId());

        List<String> emails = new ArrayList<>();
        List<String> phones = new ArrayList<>();
        for(Link link : list)
        {
            if(link.getLinkedPrecedence().equalsIgnoreCase("primary")){
                emails.add(link.getEmail().toString());
                phones.add(link.getPhone().toString());
            }
        }
        List<String> secondaryContact = list.stream()
                .filter(l -> l.getLinkedPrecedence().equalsIgnoreCase("secondary"))
                .map(l ->String.valueOf(l.getLinkedId()))
                .collect(Collectors.toList());

        Map<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("contact", Map.of(
                "primaryContactId", primaryId,
                "email", emails,
                "phoneNumber", phones,
                "SecondaryIds", secondaryContact));

        return mapper.writeValueAsString(responseMap);
    }
}
