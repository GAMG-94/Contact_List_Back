package com.gamg.contaclist.controller;

import com.gamg.contaclist.dto.ContactDTO;
import com.gamg.contaclist.entity.Contact;
import com.gamg.contaclist.exceptions.ContactNotFoundException;
import com.gamg.contaclist.service.ContactService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin
public class ContactController {

    private final ContactService contactService;

    private final ModelMapper modelMapper;

    @Autowired
    public ContactController(ContactService contactService, ModelMapper modelMapper) {
        this.contactService = contactService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/list")
    @Transactional(readOnly = true)
    public ResponseEntity<List<ContactDTO>> list() {
        List<Contact> contacts = (List<Contact>) contactService.findAll().getBody();
        List<ContactDTO> contactDTOs = new ArrayList<>();
        assert contacts != null;
        for (Contact contact : contacts) {
            contactDTOs.add(modelMapper.map(contact, ContactDTO.class));
        }
        return ResponseEntity.ok(contactDTOs);
    }

    @GetMapping("/list/{id}")
    @Transactional
    public ResponseEntity<ContactDTO> getContactById(@PathVariable Long id) throws ContactNotFoundException {
        Contact contact = contactService.getContactById(id).getBody();
        if (contact == null) {
            return ResponseEntity.notFound().build();
        }
        ContactDTO contactDTO = modelMapper.map(contact, ContactDTO.class);
        return ResponseEntity.ok(contactDTO);
    }

    @PostMapping("/new")
    @Transactional
    @Validated
    public ResponseEntity<ContactDTO> addContact(@RequestBody @Valid ContactDTO contactDTO) {
        Contact contact = modelMapper.map(contactDTO, Contact.class);
        contact = contactService.newContact(contact).getBody();
        ContactDTO responseDTO = modelMapper.map(contact, ContactDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/update/{id}")
    @Transactional
    @Validated
    public ResponseEntity<ContactDTO> updateContactById(@PathVariable Long id, @RequestBody @Valid ContactDTO contactDTO) throws ContactNotFoundException {
        Contact contact = contactService.getContactById(id).getBody();
        if (contact == null) {
            return ResponseEntity.notFound().build();
        }
        modelMapper.map(contactDTO, contact);
        contact = contactService.update(id,contact).getBody();
        ContactDTO responseDTO = modelMapper.map(contact, ContactDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Void> deleteContactById(@PathVariable(value = "id") Long id) {
        contactService.deleteContactById(id);
        return ResponseEntity.noContent().build();
    }
}
