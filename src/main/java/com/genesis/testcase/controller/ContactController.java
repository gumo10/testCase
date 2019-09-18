package com.genesis.testcase.controller;

import com.genesis.testcase.beans.Contact;
import com.genesis.testcase.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    /**
     * Create contact.
     *
     * @param contact the contact
     * @return the contact
     */
    @PostMapping("/add")
    public Contact createContact(@Valid @RequestBody Contact contact) {
        if((contact.getFreelance() && contact.getLegalinfo() != null) || (!contact.getFreelance()))
            return contactRepository.save(contact);

        return null;
    }

    /**
     * Update contact.
     *
     * @param contactId the contact id
     * @param ContactDetails the contact details
     * @return the response entity
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity updateContact(@PathVariable(value = "id") Long contactId,
                                                 @Valid @RequestBody Contact ContactDetails){
        try {
            Contact contact = contactRepository
                    .findById(contactId)
                    .orElseThrow(()
                            -> new EntityNotFoundException("Contact not found on :: " + contactId));

            if(ContactDetails.getLastname() != null)
                contact.setLastname(ContactDetails.getLastname());
            if(ContactDetails.getFirstname() != null)
                contact.setFirstname(ContactDetails.getFirstname());
            if(ContactDetails.getAddress() != null)
                contact.setAddress(ContactDetails.getAddress());
            if(ContactDetails.getLegalinfo() != null)
                contact.setLegalinfo(ContactDetails.getLegalinfo());
            if(ContactDetails.getCompanies() != null)
                contact.setCompanies(ContactDetails.getCompanies());

            final Contact updatedContact = contactRepository.save(contact);
            return ResponseEntity.ok(updatedContact);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.ok("error");
        }
    }

    /**
     * Delete contact .
     *
     * @param contactId the contact id
     * @return the map
     * @throws Exception the exception
     */
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteContact(@PathVariable(value = "id") Long contactId) throws Exception {

        Map<String, Boolean> response = new HashMap<>();

        try {
            Contact contact =
                    contactRepository
                            .findById(contactId)
                            .orElseThrow(() -> new EntityNotFoundException("Contact not found on :: " + contactId));

            contactRepository.delete(contact);
            response.put("deleted", Boolean.TRUE);

            return response;
        }
        catch(EntityNotFoundException e){
            response.put("deleted", Boolean.FALSE);

            return response;
        }
    }

}
