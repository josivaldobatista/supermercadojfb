package com.jfb.cursomc.api.services;

import com.jfb.cursomc.api.services.AbstractEmailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockMailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockMailService.class);

    @Override
    public void sendeEmail(SimpleMailMessage msg) {
        LOG.info("Simulando envio de email...");
        LOG.info(msg.toString());
        LOG.info("Email enviado!");
    }

}