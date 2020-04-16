package com.jfb.cursomc.api.services;

import com.jfb.cursomc.api.domain.Pedido;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendorderConfirmationEmail(Pedido obj);

    void sendeEmail(SimpleMailMessage msg);
}