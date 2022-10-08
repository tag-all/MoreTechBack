package ru.tagallteam.hackstarter.application.integration.modal;

import lombok.Data;

@Data
public class SendCurrency {
    private String fromPrivateKey;
    private String toPublicKey;
    private Float amount;
}
