package com.anouar.gestion_contact.services;

import com.anouar.gestion_contact.entities.Contact;
import com.anouar.gestion_contact.entities.Group;
import com.anouar.gestion_contact.repositories.ContactRepository;
import com.anouar.gestion_contact.repositories.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService{
    private GroupRepository groupRepository;
    private ContactRepository contactRepository;
    @Override
    public List<Group> getAllGroups(String name) {
        return groupRepository.findAllByNameContaining(name);
    }

    @Override
    public List<Group> getAllGroupsWithNoContacts() {
        return null;
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepository.findGroupsById(id);
    }

    @Override
    public void saveGroup(Group group) {
          groupRepository.save(group);
    }



    @Transactional
    public void deleteGroupAndUpdateContacts(Long groupId) {
        Group group = groupRepository.findById(groupId).orElse(null);
        if (group != null) {
            groupRepository.deleteGroupAndUpdateContacts(group);
            groupRepository.delete(group);
        }
    }
}
