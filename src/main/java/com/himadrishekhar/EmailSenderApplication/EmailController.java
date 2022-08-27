package com.himadrishekhar.EmailSenderApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/email")
@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @Value("${email.sender.username}")
    private String userEmail;

    @PostMapping(value = "/sendMail")
    public Response sendMail(@RequestBody Request request){
        System.out.println("Request Received : {}" + request.getMailReceiver() + ", " +
                request.getMailBody() + ", " + request.getMailSubject() + ", " + userEmail);

        try{
            emailService.sendMail(userEmail, request.getMailReceiver(), request.getMailBody(),
                    request.getMailSubject(),request.getMailCount());
        }
        catch (IOException e){
            e.printStackTrace();
            return new Response("Please Retry Again Later", 404);
        }

        return new Response("Request Received", 200);
    }
}
