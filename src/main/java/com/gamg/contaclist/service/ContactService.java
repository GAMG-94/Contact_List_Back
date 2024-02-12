package com.gamg.contaclist.service;

import com.gamg.contaclist.entity.Contact;
import com.gamg.contaclist.exceptions.ContactNotFoundById;
import com.gamg.contaclist.exceptions.ContactNotFoundException;
import com.gamg.contaclist.exceptions.ContactServiceException;
import com.gamg.contaclist.repository.IContactRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactService {

    private final IContactRepository contactRepository;

    @Autowired
    public ContactService(IContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ResponseEntity<Iterable<Contact>> findAll() {
        Iterable<Contact> contact = contactRepository.findAll();
        return ResponseEntity.ok(contact);
    }

    public ResponseEntity<Contact> getContactById(Long id) throws ContactNotFoundException {
        try {
            Optional<Contact> contacto = contactRepository.findById(id);
            return contacto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (ContactNotFoundById e) {
            throw new ContactNotFoundException("Usuario no encontrado");
        }
    }

    public ResponseEntity<Contact> newContact(Contact nuevoContacto) {
        Contact contactoGuardado = contactRepository.save(nuevoContacto);
        return ResponseEntity.ok(contactoGuardado);
    }

    public ResponseEntity<Contact> update(Long id, Contact contactoActualizado) {
        try {
            Optional<Contact> contactoOptional = contactRepository.findById(id);
            if (contactoOptional.isPresent()) {
                Contact contactoExistente = contactoOptional.get();
                contactoExistente.setName(contactoActualizado.getName());
                contactoExistente.setEmail(contactoActualizado.getEmail());
                contactoExistente.setNumberPhone(contactoActualizado.getNumberPhone());
                contactoExistente.setDateRegister(contactoActualizado.getDateRegister());
                Contact contactoGuardado = contactRepository.save(contactoExistente);
                return ResponseEntity.ok(contactoGuardado);
            } else {
                throw new EntityNotFoundException("Contacto con el id: " + id + " no ha sido encontrado");
            }
        } catch (DataIntegrityViolationException e) {
            throw new ContactServiceException("Violación de la integridad de los datos: " + e.getMessage());
        }
    }

    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
        ResponseEntity.noContent().build();
    }
}
