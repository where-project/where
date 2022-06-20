package com.where.where.controller;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.where.where.dto.model.ReservationModel;
import com.where.where.service.EmailService;

import java.util.Random;

@RestController
@RequestMapping("/api/v1/email-sender")
public class EmailController {
	private Logger logger = LoggerFactory.getLogger(EmailController.class);
	@Autowired
	private EmailService emailService;

	@PostMapping("/send-email")
	public boolean sendEmail(@Valid @RequestBody ReservationModel reservationModel) {
		try {
			Random random = new Random();
			emailService.sendEmail(reservationModel.getEmail(),
					"The reservation you have made to the " + reservationModel.getPlaceName() + " on "
							+ reservationModel.getReservationDate() + " at " + reservationModel.getReservationTime()
							+ " time has been completed. You can log in with the " + Math.abs(random.nextInt()) + " code.",
					"Reservation Details WHERE");
		} catch (MailException e) {
			logger.info(e.getMessage());
		}
		return true;
	}

	@PostMapping("/send-email-with-attachment/{email}")
	public String sendEmailWithAttachment(@PathVariable String email) throws MessagingException {
		emailService.sendEmailWithAttachment(email,
				"Merhaba bu bir Where projesi "
						+ "mail gönderme sistemi testidir. Kaptan pilotunuz ONUR konuşmaktadır. "
						+ "Lütfen mesaji dikkate almayınız !!! This email has attachment",
				"Payment Details WHERE", "C:\\\\Users\\\\onura\\\\Desktop\\\\where\\\\Email\\\\wh.png");
		return "Mail sent successfully";
	}
}
