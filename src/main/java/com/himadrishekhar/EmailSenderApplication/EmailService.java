package com.himadrishekhar.EmailSenderApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String sender, String receiver, String body, String subject, int count) throws IOException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(receiver);
        mailMessage.setText(body);
        mailMessage.setSubject(subject);

        for (int i =0; i < count; i++){
            mailSender.send(mailMessage);
            System.out.println((i+1) + " Mails Sent!");
        }
    }
}
