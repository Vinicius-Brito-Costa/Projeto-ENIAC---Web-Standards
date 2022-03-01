package br.com.tectoy.megadrivepublicity.controller;

import br.com.tectoy.megadrivepublicity.controller.response.DefaultResponse;
import br.com.tectoy.megadrivepublicity.model.Contact;
import br.com.tectoy.megadrivepublicity.model.dao.ContactDao;
import br.com.tectoy.megadrivepublicity.repository.ContactRepository;
import br.com.tectoy.megadrivepublicity.repository.EmailRepository;
import br.com.tectoy.megadrivepublicity.service.EmailService;
import br.com.tectoy.megadrivepublicity.validator.ContactValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/contact")
@RestController
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private EmailRepository emailRepository;

    private final EmailService emailService = new EmailService();

    @GetMapping
    public List<Contact> getContacts(){
        return contactRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getContact(@PathVariable(value = "id") long id){
        Optional<Contact> contact = contactRepository.findById(id);
        if(contact.isPresent()){
            return ResponseEntity.ok().body(contact.get());
        }
        return ResponseEntity.ok().body(new DefaultResponse(1, "Contato não existe."));
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<DefaultResponse> addContact(@RequestBody(required = true) ContactDao newContact) {
        if(ContactValidator.validateContact(newContact)){
            contactRepository.saveAndFlush(
                    new Contact(newContact, emailService.addEmail(emailRepository, newContact.getEmail())));
            return ResponseEntity.ok().body(new DefaultResponse(0, "Sua mensagem foi adicionada com sucesso."));
        }
        return ResponseEntity.badRequest().body(new DefaultResponse(1, "Contato inválido."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> removeContact(@PathVariable(value = "id") long id){
        Optional<Contact> contact = contactRepository.findById(id);
        if(contact.isPresent()){
            contactRepository.deleteById(id);
            return ResponseEntity.ok().body(new DefaultResponse(0, "Contato deletado."));
        }

        return ResponseEntity.badRequest().body(new DefaultResponse(1, "Contato não existe."));
    }
}
