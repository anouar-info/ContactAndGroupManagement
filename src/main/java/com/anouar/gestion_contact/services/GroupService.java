package com.anouar.gestion_contact.services;

import com.anouar.gestion_contact.entities.Contact;
import com.anouar.gestion_contact.entities.Group;

import java.util.List;

public interface GroupService {
    List<Group> getAllGroups(String name);
    List<Group> getAllGroupsWithNoContacts();
    Group getGroupById(Long id);
    void saveGroup(Group group);

    void deleteGroupAndUpdateContacts(Long groupId);
}
