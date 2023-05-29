package com.anouar.gestion_contact.repositories;

import com.anouar.gestion_contact.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Long> {
    List<Contact> findAllByOrderByNomAsc();

    List<Contact> findAllByNomContainingOrderByNomAsc(String s);
    @Query("SELECT c FROM Contact c WHERE c.group IS NOT NULL")
    List<Contact> findAllWithGroupNotNull();
    void deleteById(Long id);
    List<Contact> findByTelephone1ContainingOrTelephone2ContainingOrderByNomAsc(String number1,String number2);
    List<Contact> findAllByNomContainingAndTelephone1ContainingOrTelephone2ContainingOrderByNomAsc(String s1,String tel1,String tel2);
}
