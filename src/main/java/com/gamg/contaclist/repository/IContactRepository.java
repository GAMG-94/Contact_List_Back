package com.gamg.contaclist.repository;

import com.gamg.contaclist.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContactRepository extends CrudRepository<Contact, Long> {
}
