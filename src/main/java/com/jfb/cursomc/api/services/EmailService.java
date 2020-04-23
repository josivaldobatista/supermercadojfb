package com.jfb.cursomc.api.services;

import javax.mail.internet.MimeMessage;

import com.jfb.cursomc.api.domain.Cliente;
import com.jfb.cursomc.api.domain.Pedido;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendorderConfirmationEmail(Pedido obj);

    void sendeEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(Pedido obj);

    void sendHtmlEmail(MimeMessage msg);

    void sendNewPasswordEmail(Cliente cliente, String newPass);
}