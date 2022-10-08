package ru.tagallteam.hackstarter.application.transfer.model;

import lombok.Data;

@Data
public class TransferCreateModel {
    private TransferType type;
    private Long nftId;
    private Float amount;
}
