package com.anouar.gestion_contact;

import com.anouar.gestion_contact.entities.Contact;
import com.anouar.gestion_contact.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GestionContactApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GestionContactApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

       /* contactRepository.save(new Contact(null,"Faraji","Anouar","0612345678","0687654321","meknes","anouar@gmail.com","anouar@google.com",true));
        contactRepository.save(new Contact(null,"kamili","kamal","0612156678","0687589421","fes","anouar@gmail.com","anouar@google.com",true));*/



    }
}
