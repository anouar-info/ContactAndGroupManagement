package com.anouar.gestion_contact.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Table(name = "CONTACTS")
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "First name is required.")
    private String nom;

    @NotBlank(message = "Last name is required.")
    private String prenom;

    @NotBlank(message = "Professional telephone is required.")
    @Pattern(regexp = "^(06|07|05)\\d{8}$", message = "Invalid telephone format. It should start with 06, 07, or 05 and have 8 digits.")
    private String telephone1;

    @Pattern(regexp = "^(06|07|05)\\d{8}$", message = "Invalid telephone format. It should start with 06, 07, or 05 and have 8 digits.")
    private String telephone2;

    @NotBlank(message = "Address is required.")
    private String adresse;

    @NotBlank(message = "Personal email is required.")
    @Email(message = "Invalid email format. Please provide a valid email address.")
    private String emailPersonnel;

    @NotBlank(message = "Professional email is required.")
    @Email(message = "Invalid email format. Please provide a valid email address.")
    private String emailProfessionnel;


    private boolean genre;
    @ManyToOne
    private Group group;
    @Override
    public int hashCode() {
        return Objects.hash(id); // Exclude 'group' field from hashCode calculation
    }
}

