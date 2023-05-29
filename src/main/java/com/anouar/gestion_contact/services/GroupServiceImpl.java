package com.anouar.gestion_contact.services;

import com.anouar.gestion_contact.entities.Contact;
import com.anouar.gestion_contact.entities.Group;
import com.anouar.gestion_contact.repositories.ContactRepository;
import com.anouar.gestion_contact.repositories.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public void saveGroup(Group group) {
          groupRepository.save(group);
    }

    @Override
    public void deleteGroup(Long id) {
          groupRepository.deleteById(id);
    }
}
