package com.anouar.gestion_contact.services;
import com.anouar.gestion_contact.entities.Contact;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public interface ContactService {
        List<Contact> getAllContacts(String name,String tel);
        List<Contact> getAllContactsWithoutGroup();
        ArrayList<ArrayList<Object>> getAllContactsGroupedByGroup(List<Contact> contacts ,String s);

        List<Contact> getAllContactsWithGroupNotNull();
        Contact getContactById(Long id);
        List<Contact> getContactsByIds(List<Long> ids);
        void saveContact(Contact contact);
        void deleteContact(Long id);


}
