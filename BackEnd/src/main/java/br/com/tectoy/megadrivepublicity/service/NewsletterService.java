package br.com.tectoy.megadrivepublicity.service;

import br.com.tectoy.megadrivepublicity.config.AwsCredentials;
import br.com.tectoy.megadrivepublicity.config.AwsProperties;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;

public class NewsletterService {

    private final String AWS_REGION = "sa-east-1";
    private final String ENCODER = "UTF-8";

    public void sendEmailWithAwsSES(String email, String titulo, String corpoDaMensagem, AwsProperties awsProperties){
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
