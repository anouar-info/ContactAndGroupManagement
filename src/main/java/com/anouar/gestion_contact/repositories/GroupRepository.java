package com.anouar.gestion_contact.repositories;

import com.anouar.gestion_contact.entities.Contact;
import com.anouar.gestion_contact.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Long> {
      List<Group> findAllByNameContaining(String search);
     // List<Group> findAllByContacts_Empty();
      Group findGroupsById(Long id);
    @Modifying
    @Query("UPDATE Contact c SET c.group = null WHERE c.group = :group")
    void deleteGroupAndUpdateContacts(@Param("group") Group group);
}
