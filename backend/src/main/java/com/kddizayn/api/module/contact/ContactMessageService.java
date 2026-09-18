package com.kddizayn.api.module.contact;

import com.kddizayn.api.exception.ResourceNotFoundException;
import com.kddizayn.api.module.contact.dto.ContactMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private final ContactRepository contactRepository;
    private final JavaMailSender mailSender;

    public void send(ContactMessageRequest request) {
        ContactInfo contact = contactRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Contact info not configured yet"));

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(contact.getEmail());
        mail.setReplyTo(request.getEmail());
        mail.setSubject("Web sitesi iletişim: " + request.getName());
        mail.setText("Ad: " + request.getName()
                + "\nE-posta: " + request.getEmail()
                + "\n\nMesaj:\n" + request.getMessage());
        mailSender.send(mail);
    }
}
