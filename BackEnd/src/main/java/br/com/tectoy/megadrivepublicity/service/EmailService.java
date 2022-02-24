package br.com.tectoy.megadrivepublicity.service;

import br.com.tectoy.megadrivepublicity.controller.response.DefaultResponse;
import br.com.tectoy.megadrivepublicity.model.Email;
import br.com.tectoy.megadrivepublicity.repository.EmailRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailService {

    public Email addEmail(EmailRepository emailRepository, String email){
        Optional<Email> emailExists = emailRepository.findByEmail(email);
        if(emailExists.isPresent()){
            return emailExists.get();
        }
        Email newEmail = new Email();
        newEmail.setEmail(email);
        return emailRepository.saveAndFlush(newEmail);
    }

    public ResponseEntity<DefaultResponse> addEmailToNewsletter(EmailRepository emailRepository, String email){
        Optional<Email> emailExists = emailRepository.findByEmail(email);
        if(emailExists.isPresent()){
            Email currentEmail = emailExists.get();
            if(currentEmail.isNewsletterSigned()){
                return ResponseEntity.badRequest().body(new DefaultResponse(2, "O E-mail já está registrado na nossa Newsletter"));
            }
            currentEmail.setNewsletterSigned(true);
            emailRepository.saveAndFlush(currentEmail);
            return ResponseEntity.ok().body(new DefaultResponse(1, "O E-mail já está registrado em nossa base, iremos adicionar na Newsletter!"));
        }
        Email newEmail = new Email();
        newEmail.setEmail(email);
        emailRepository.saveAndFlush(newEmail);
        return ResponseEntity.ok().body(new DefaultResponse(0, "E-mail registrado na Newsletter!"));
    }
}
