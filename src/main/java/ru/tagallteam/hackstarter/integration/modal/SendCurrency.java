package ru.tagallteam.hackstarter.integration.modal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendCurrency {
    private String fromPrivateKey;
    private String toPublicKey;
    private Float amount;
}
