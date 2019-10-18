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
}
