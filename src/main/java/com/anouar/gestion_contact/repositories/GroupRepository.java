package com.anouar.gestion_contact.repositories;

import com.anouar.gestion_contact.entities.Contact;
import com.anouar.gestion_contact.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Long> {
      List<Group> findAllByNameContaining(String search);
}
