package ru.tagallteam.hackstarter.integration.modal;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class NftCreateTransaction {
    @JsonAlias("transaction_hash")
    private String transactionHash;
}
