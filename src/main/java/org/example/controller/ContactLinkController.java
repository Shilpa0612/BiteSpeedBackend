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
//@RequestMapping("/api/identify")
public class ContactLinkController {
    @Autowired
    private final ContactService contactService;
    @Autowired
    private final LinkService linkService;

   // @Autowired
    public ContactLinkController(ContactService contactService, LinkService linkService)
    {
        this.contactService = contactService;
        this.linkService = linkService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public ResponseEntity<String> mapContact(@RequestBody ContactDTO contactDTO) throws JsonProcessingException {

      String email = contactDto.getEmail();
      String phone = contactDto.getPhone();

      System.out.println(email + " : "+ phone);
      boolean ifExists = contactService.returnIfIdExist(email, phone);

      if (ifExists) {
          Integer id = contactService.getIdFromMailPhone(email, phone);
          Contact contactEntity = contactService.getMatchedContact(id);

          Link linkInstance = new Link();
          linkInstance.setLinkedId(id);
          linkInstance.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
          linkInstance.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));

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
          contactInstance.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
          contactInstance.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
          contactInstance.setEmail(email);
          contactService.saveOrCreate(contactInstance);
      }

        //List<Contact> contactEntities = contactService.getMatchedContact()
        List<Contact> contactEntities = new ArrayList<>();
        contactEntities = contactService.getAllContacts();

        List<Integer> contactIds = contactEntities.stream()
                .map(Contact::getId)
                .collect(Collectors.toList());
        Map<Integer, List<Link>> linksByContactId = linkService.getAllLinksForContacts(contactIds);

        List<String> response = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        for(Contact c: contactEntities)
        {
            List<Link> linkEntities = linkService.getAllLink(c.getId());
//            System.out.println("linkEntity: "+linkEntities.toString());
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
        List<String> secondaryContact = new ArrayList<>();
        for(Link link : list)
        {
            if(link.getLinkedPrecedence().equalsIgnoreCase("primary")){
                emails.add(link.getEmail().toString());
                phones.add(link.getPhone().toString());
            }
            if(link.getLinkedPrecedence().equalsIgnoreCase("secondary"))
                secondaryContact.add(link.getLinkedId()+"");
        }
        /*List<String> secondaryContact = list.stream()
                .filter(l -> l.getLinkedPrecedence().equalsIgnoreCase("secondary"))
                .map(l ->String.valueOf(l.getLinkedId()))
                .collect(Collectors.toList());*/

        Map<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("contact", Map.of(
                "primaryContactId", primaryId,
                "email", emails,
                "phoneNumber", phones,
                "SecondaryIds", secondaryContact,
                "createdAt", contact.getCreatedAt(),
                "updatedAt", contact.getUpdatedAt(),
                "deletedAt", contact.getDeletedAt()));

        return mapper.writeValueAsString(responseMap);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        // Get the validation errors from the exception object
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        // Create a list of error messages
        List<String> errorMessages = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            errorMessages.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }
        // Return a response entity with status 400 and error messages
        return new ResponseEntity<>(String.join(", ", errorMessages), HttpStatus.BAD_REQUEST);
    }
}
