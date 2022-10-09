package ru.tagallteam.hackstarter.application.user.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BalanceNftDto {
    private String name;

    private String uri;

    private String txHash;
}
