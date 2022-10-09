package ru.tagallteam.hackstarter.integration.modal;

import lombok.Data;

@Data
public class NftTokenDto {
    private Long tokenId;
    private String uri;
    private String publicKey;
}
