package com.where.where.service;

import java.io.File;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.where.where.exception.AttachmentNotFoundException;
import com.where.where.exception.EmailException;
import com.where.where.repository.SettingsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    private final SettingsRepository settingsRepository;

    private JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(settingsRepository.findByKey("mailHost").getValue());
        mailSender.setPort(Integer.parseInt(settingsRepository.findByKey("mailPort").getValue()));
        mailSender.setUsername(settingsRepository.findByKey("mailUsername").getValue());
        mailSender.setPassword(settingsRepository.findByKey("mailPassword").getValue());
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        return mailSender;
    }

    public void sendEmail(String toEmail, String body, String subject) throws MailException {
        JavaMailSender javaMailSender = getJavaMailSender();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail); // the email to which u wish to send the email
        message.setFrom(settingsRepository.findByKey("mailFrom").getValue()); // from which the email needs to be sent
        message.setText(body);
        message.setSubject(subject);
        javaMailSender.send(message);
    }

    public void sendEmailWithAttachment(String toEmail,
                                        String body,
                                        String subject,
                                        String attachment) throws MessagingException {
        if (attachment == null) {
            throw new AttachmentNotFoundException("File does not exists");
        }
        JavaMailSender javaMailSender = getJavaMailSender();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);
        String mailFrom = settingsRepository.findByKey("mailFrom").getValue();
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setFrom(mailFrom);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);
        FileSystemResource fileSystemResource
                = new FileSystemResource(new File(attachment));
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
        try {
            javaMailSender.send(mimeMessage);
            log.info(String.format("The e-mail with the subject %s was successfully sent from the e-mail address %s to the e-mail address %s",
                    subject, mailFrom, toEmail));
        } catch (Exception ex) {
            throw new EmailException("Failed to send email");
        }
    }
}
