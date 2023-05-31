package com.anouar.gestion_contact.controllers;

import com.anouar.gestion_contact.entities.Contact;
import com.anouar.gestion_contact.entities.Group;
import com.anouar.gestion_contact.repositories.ContactRepository;
import com.anouar.gestion_contact.repositories.GroupRepository;
import com.anouar.gestion_contact.services.ContactService;
import com.anouar.gestion_contact.services.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.*;

@Controller
@AllArgsConstructor
@RequestMapping()
public class ContactController {
    private ContactService contactService;
    private GroupService groupService;
    @GetMapping("/contacts")
        public String contacts(Model model, @RequestParam(name="search",defaultValue = "") String s, @RequestParam(name="tel",defaultValue = "")String tel) {
        List<Contact> contactList=contactService.getAllContacts(s,tel);
        model.addAttribute("Contacts", contactList);
        model.addAttribute("search", s);
        model.addAttribute("tel", tel);
        return "contacts";
    }

    @GetMapping("/groups")
    public String groups(Model model, @RequestParam(name="search",defaultValue = "") String s ) {
        model.addAttribute("Groups", contactService.getAllContactsGroupedByGroup(contactService.getAllContactsWithGroupNotNull(),s));
        System.out.println("ana  hiya group by \t"+model.getAttribute("Groups"));
        model.addAttribute("search", s);
        return "groups";
    }
    @GetMapping("/deleteContact")
        public String deleteContact(@RequestParam(name="contactId") Long id, @RequestParam(name="search",defaultValue = "") String s, @RequestParam(name="tel",defaultValue = "")String tel){

        contactService.deleteContact(id);

        return "redirect:/contacts?search="+s+"&tel="+tel;
    }
    @GetMapping("/updateContact")
    public String updateContact(Model model,@RequestParam(name="contactId") Long id){
        Contact contact =contactService.getContactById(id);
        model.addAttribute("contact",contact);
        return "updateContact";
    }
    @GetMapping("/updateGroup")
    public String updateGroup(Model model,@RequestParam(name="groupId") Long id){
        Group group =groupService.getGroupById(id);
        model.addAttribute("group",group);
        return "updateContact";
    }
    @GetMapping("/deleteGroup")
    public String deleteGroup(@RequestParam(name="groupId") Long id){
        groupService.deleteGroupAndUpdateContacts(id);
        return "redirect:/groups";
    }
    @GetMapping("/")
    public String home(){
        return "redirect:/contacts";
    }
    @GetMapping("/addContact")
    public String addContact(Model model){
        model.addAttribute("contact",new Contact());
        return "addContact";
    }
    @GetMapping("/addGroup")
    public String addGroup(Model model){
        model.addAttribute("group",new Group());
        return "addGroup";
    }
    @PostMapping("saveContact")
    public String saveContact(@Valid Contact contact, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addContact";
        }
        contactService.saveContact(contact);
        return  "redirect:/contacts?search="+contact.getNom();
    }
    @PostMapping("/saveGroup")
    public String saveGroup(@Valid Group group, @RequestParam(value = "contacts", required = false) List<Long> contactIds,BindingResult bindingResult) {
        System.out.println("7na l'ids li selectiti \t "+contactIds);
        if(bindingResult.hasErrors()){
            return "addGroup";}
        groupService.saveGroup(group);

        if (contactIds != null && !contactIds.isEmpty()) {
            List<Contact> contacts = contactService.getContactsByIds(contactIds);
            group.addContacts(contacts);
            //System.out.println("ana lgroup li ghaytzad normalement \t "+group);
            groupService.saveGroup(group);
        }

        return "redirect:/groups";
    }

    @ModelAttribute("getContacts")
    public List<Contact> getContacts() {
        return contactService.getAllContacts("","");
    }
    @ModelAttribute("getContactsWithoutGroup")
    public List<Contact> getContactsWithoutGroup() {
        return contactService.getAllContactsWithoutGroup();
    }

}
