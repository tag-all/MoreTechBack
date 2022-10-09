package ru.tagallteam.hackstarter.integration.modal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NftCreate {
    private String toPublicKey;
    private String uri;
    private Long nftCount;
}
