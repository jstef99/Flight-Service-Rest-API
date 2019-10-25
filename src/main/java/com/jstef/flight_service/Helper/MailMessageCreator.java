package com.jstef.flight_service.Helper;

import com.jstef.flight_service.Entity.Registration;
import com.jstef.flight_service.Entity.User;
import org.springframework.mail.SimpleMailMessage;

public class MailMessageCreator {
    public SimpleMailMessage createRegistrationMsg(User user, Registration registration, String paymentLink){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Flight reservation.");
        message.setText("Good morning " + user.getFirstName() + " " + user.getLastName() + ". You have succesfully " +
                "registered for a flight. In order to finalize your registration, please click link below. \n" +
                paymentLink + "\nAs transfer topic please provide your reservation ID. \n" + "\n Flight Service");
        return message;
    }
    public SimpleMailMessage createApiKeyMsg(User user, String key){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Api key generation.");
        message.setText("Good morning " + user.getFirstName() + " " + user.getLastName() + ". Api key has been granted " +
                "and generated for your account: " + key+"\n Flight Service");
        return message;
    }
}
