package com.where.where.controller;

import com.where.where.model.BaseUser;
import com.where.where.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("/api/v1/email-sender")
public class EmailController {
    private Logger logger = LoggerFactory.getLogger(EmailController.class);
    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public String sendEmail(@RequestParam String email) {
        try {
            BaseUser user = new BaseUser();
            emailService.sendEmail(user, email, "Merhaba bu bir Where projesi " +
                            "mail gönderme sistemi testidir. Kaptan pilotunuz ONUR konuşmaktadır. " +
                            "Lütfen mesaji dikkate almayınız !!!",
                    "Payment Details WHERE");
        } catch (MailException e) {
            logger.info(e.getMessage());
        }
        return "Mail sent successfully";
    }

    @PostMapping("/send-email-with-attachment")
    public String sendEmailWithAttachment(@RequestParam String email) throws MessagingException {
        try {
            BaseUser user = new BaseUser();
            emailService.sendEmailWithAttachment(user, email, "Merhaba bu bir Where projesi " +
                            "mail gönderme sistemi testidir. Kaptan pilotunuz ONUR konuşmaktadır. " +
                            "Lütfen mesaji dikkate almayınız !!! This email has attachment",
                    "Payment Details WHERE",
                    "C:\\\\Users\\\\onura\\\\Desktop\\\\where\\\\Email\\\\wh.png");
        } catch (MailException e) {
            logger.info(e.getMessage());
        }
        return "Mail sent successfully";
    }
}
