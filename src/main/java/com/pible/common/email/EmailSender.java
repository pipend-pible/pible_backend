package com.pible.common.email;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSender {
    private final MailProperties properties;
    private final JavaMailSender javaMailSender;

    public void send(String email, String text) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(properties.getUsername());
        message.setTo(email);
        message.setSubject("Pible 인증코드 발송");
        message.setText(text);

        javaMailSender.send(message);
    }
}
