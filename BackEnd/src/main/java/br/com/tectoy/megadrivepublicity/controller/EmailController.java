package br.com.tectoy.megadrivepublicity.controller;

import br.com.tectoy.megadrivepublicity.config.AwsCredentials;
import br.com.tectoy.megadrivepublicity.config.AwsProperties;
import br.com.tectoy.megadrivepublicity.controller.response.DefaultResponse;
import br.com.tectoy.megadrivepublicity.model.dao.EmailDao;
import br.com.tectoy.megadrivepublicity.model.dao.NewsletterDao;
import br.com.tectoy.megadrivepublicity.model.dao.NewsletterSingleEmailDao;
import br.com.tectoy.megadrivepublicity.repository.EmailRepository;
import br.com.tectoy.megadrivepublicity.service.EmailService;
import br.com.tectoy.megadrivepublicity.validator.EmailValidator;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
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

    private final String AWS_REGION = "sa-east-1";
    private final String ENCODER = "UTF-8";

    private final EmailService emailService = new EmailService();

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
        emailRepository.findAll().forEach(email -> sendEmailWithAwsSES(email.getEmail(), newsletter.getTitulo(), newsletter.getMensagem()));
        return ResponseEntity.ok().body(new DefaultResponse(0, "E-mails enviados."));
    }

    @PostMapping("/newsletter/single-email")
    public ResponseEntity<DefaultResponse> sendNewsletterEmails(@RequestBody NewsletterSingleEmailDao newsletter){
        if(EmailValidator.isValidEmail(newsletter.getEmail())){
            emailService.addEmailToNewsletter(emailRepository, newsletter.getEmail());
            sendEmailWithAwsSES(newsletter.getEmail(), newsletter.getTitulo(), newsletter.getMensagem());
            return ResponseEntity.ok().body(new DefaultResponse(0, "E-mail enviado."));
        }
        return ResponseEntity.badRequest().body(new DefaultResponse(1, "E-mail não é válido."));
    }

    private void sendEmailWithAwsSES(String email, String titulo, String corpoDaMensagem){
        AmazonSimpleEmailService awsSes = AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(AwsCredentials.getInstance(awsProperties).getCredentials()))
                .withRegion(AWS_REGION)
                .build();
        Destination destination = new Destination().withToAddresses(email);
        Body body = new Body().withHtml(new Content().withCharset(ENCODER).withData(corpoDaMensagem));
        Message message = new Message()
                .withBody(body)
                .withSubject(new Content().withCharset(ENCODER).withData(titulo));

        SendEmailRequest emailRequest = new SendEmailRequest()
                .withDestination(destination)
                .withMessage(message)
                .withSource(awsProperties.getEmail());

        awsSes.sendEmail(emailRequest);
    }
}
