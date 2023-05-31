package com.anouar.gestion_contact.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "GROUPS", uniqueConstraints = {
        @UniqueConstraint(name = "uk_name", columnNames = {"name"})
})
@AllArgsConstructor @NoArgsConstructor @Data @ToString(exclude = "contacts")
public class Group {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="name is mandatory")
    @Column(unique=true)
    private String name;

    @OneToMany(mappedBy="group")
    private Set<Contact> contacts=new HashSet<>();
    @Override
    public int hashCode() {
        return Objects.hash(id, name); // Exclude 'contacts' field from hashCode calculation
    }
    public void addContact(Contact contact) {
        contacts.add(contact);
        contact.setGroup(this);
    }
    public void addContacts(List<Contact> contacts) {
        for (Contact contact : contacts) {
            this.addContact(contact);
        }
    }
    public void removeContact(Contact contact) {
        contacts.remove(contact);
        contact.setGroup(null);
    }


}
