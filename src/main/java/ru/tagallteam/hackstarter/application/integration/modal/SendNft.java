package ru.tagallteam.hackstarter.application.integration.modal;

import lombok.Data;

@Data
public class SendNft {
    private String fromPrivateKey;
    private String toPublicKey;
    private Long tokenId;
}
