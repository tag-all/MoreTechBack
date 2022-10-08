package ru.tagallteam.hackstarter.integration.modal;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import lombok.Data;

@Data
public class NftGenerateInfo {
    @JsonFormat(pattern = "wallet_id")
    private String walletId;

    private List<Long> tokens;
}
