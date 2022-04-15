package com.example.bootcamp.services;
import com.example.bootcamp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service("emailSenderService")
public class EmailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) throws MailException {
        javaMailSender.send(email);
    }


    public void sendMail(String to,String subject,String text)
    {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    SimpleMailMessage mail = new SimpleMailMessage();
                    mail.setTo(to);
                    mail.setFrom("arorahoney6465@gmail.com");
                    mail.setSubject(subject);
                    mail.setText(text);
                    javaMailSender.send(mail);
                } catch (MailException e) {
                }
            }
        });
    }
}
