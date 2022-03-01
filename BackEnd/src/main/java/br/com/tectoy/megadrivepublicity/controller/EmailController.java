package br.com.tectoy.megadrivepublicity.controller;

import br.com.tectoy.megadrivepublicity.config.AwsProperties;
import br.com.tectoy.megadrivepublicity.controller.response.DefaultResponse;
import br.com.tectoy.megadrivepublicity.model.dao.EmailDao;
import br.com.tectoy.megadrivepublicity.model.dao.NewsletterDao;
import br.com.tectoy.megadrivepublicity.model.dao.NewsletterSingleEmailDao;
import br.com.tectoy.megadrivepublicity.repository.EmailRepository;
import br.com.tectoy.megadrivepublicity.service.EmailService;
import br.com.tectoy.megadrivepublicity.service.NewsletterService;
import br.com.tectoy.megadrivepublicity.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/email")
@RestController
public class EmailController {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private AwsProperties awsProperties;

    private final EmailService emailService = new EmailService();

    private final NewsletterService newsletterService = new NewsletterService();

    @PostMapping
    @CrossOrigin
    public ResponseEntity<DefaultResponse> addEmailToNewsletter(@RequestBody EmailDao emailDao){
        if(EmailValidator.isValidEmail(emailDao.getEmail())){
            return emailService.addEmailToNewsletter(emailRepository, emailDao.getEmail());
        }

        return ResponseEntity.badRequest().body(new DefaultResponse(3, "E-mail inválido ou em branco."));
    }

    @PostMapping("/newsletter")
    public ResponseEntity<DefaultResponse> sendNewsletterEmails(@RequestBody NewsletterDao newsletter){
        emailRepository.findAll().forEach(email -> newsletterService.sendEmailWithAwsSES(email.getEmail(), newsletter.getTitulo(), newsletter.getMensagem(), awsProperties));
        return ResponseEntity.ok().body(new DefaultResponse(0, "E-mails enviados."));
    }

    @PostMapping("/newsletter/single-email")
    public ResponseEntity<DefaultResponse> sendNewsletterEmails(@RequestBody NewsletterSingleEmailDao newsletter){
        if(EmailValidator.isValidEmail(newsletter.getEmail())){
            emailService.addEmailToNewsletter(emailRepository, newsletter.getEmail());
            newsletterService.sendEmailWithAwsSES(newsletter.getEmail(), newsletter.getTitulo(), newsletter.getMensagem(), awsProperties);
            return ResponseEntity.ok().body(new DefaultResponse(0, "E-mail enviado."));
        }
        return ResponseEntity.badRequest().body(new DefaultResponse(1, "E-mail não é válido."));
    }
}
