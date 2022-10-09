package ru.tagallteam.hackstarter.application.nft.model;

import lombok.Data;

@Data
public class NftDto {
    private String name;
    private String txHash;
    private Long price;
    private Long clanId;
    private String uri;
}
