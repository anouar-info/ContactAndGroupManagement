package com.anouar.gestion_contact.services;

import com.anouar.gestion_contact.entities.Contact;
import com.anouar.gestion_contact.repositories.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService{

    private ContactRepository contactRepository;
    @Override
    public List<Contact> getAllContacts(String name, String tel) {
        List<Contact> contactList;

        if (!name.isEmpty() && !tel.isEmpty()) {
            contactList = contactRepository.findAllByNomContainingAndTelephone1ContainingOrTelephone2ContainingOrderByNomAsc(name, tel, tel);
        } else if (!name.isEmpty()) {
            contactList = contactRepository.findAllByNomContainingOrderByNomAsc(name);
        } else if (!tel.isEmpty()) {
            contactList = contactRepository.findByTelephone1ContainingOrTelephone2ContainingOrderByNomAsc(tel, tel);
        } else {
            contactList = contactRepository.findAllByOrderByNomAsc();
        }
        return contactList;
    }

    @Override
    public ArrayList<ArrayList<Object>> getAllContactsGroupedByGroup(List<Contact> contacts,String s) {
        ArrayList<ArrayList<Object>> contactsGrouped = new ArrayList<ArrayList<Object>>();
        for(int i=0;i<contacts.toArray().length;i++){
            if(contacts.get(i).getGroup().getName().contains(s)){
            ArrayList<Object> contact = new ArrayList<Object>();
            contact.add(contacts.get(i).getNom());
            contact.add(contacts.get(i).getPrenom());
            contact.add(contacts.get(i).getEmailPersonnel());
            contact.add(contacts.get(i).getEmailProfessionnel());
            contact.add(contacts.get(i).getAdresse());
            contact.add(contacts.get(i).getTelephone1());
            contact.add(contacts.get(i).getTelephone2());
            contact.add(contacts.get(i).isGenre());
            contact.add(contacts.get(i).getGroup().getName());
            contact.add(contacts.get(i).getId());
            contactsGrouped.add(contact);
        }}
        return contactsGrouped;
    }

    @Override
    public List<Contact> getAllContactsWithGroupNotNull() {
        return contactRepository.findAllWithGroupNotNull();
    }

    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findById(id).get();
    }
    @Override
    public List<Contact> getContactsByIds(List<Long> ids) {
        return contactRepository.findAllById(ids);
    }

    @Override
    public void saveContact(Contact contact) {
         contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
