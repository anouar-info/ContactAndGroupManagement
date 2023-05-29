package com.anouar.gestion_contact.services;

import com.anouar.gestion_contact.entities.Contact;
import com.anouar.gestion_contact.entities.Group;

import java.util.List;

public interface GroupService {
    List<Group> getAllGroups(String name);
    void saveGroup(Group group);
    void deleteGroup(Long id);
}
