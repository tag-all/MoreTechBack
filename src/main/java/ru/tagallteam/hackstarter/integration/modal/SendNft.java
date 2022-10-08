package ru.tagallteam.hackstarter.integration.modal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendNft {
    private String fromPrivateKey;
    private String toPublicKey;
    private Long tokenId;
}
