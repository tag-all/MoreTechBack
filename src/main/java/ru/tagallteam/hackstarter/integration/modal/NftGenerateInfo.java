package ru.tagallteam.hackstarter.integration.modal;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;
import lombok.Data;

@Data
public class NftGenerateInfo {
    @JsonAlias("wallet_id")
    private String walletId;

    private List<Long> tokens;
}
