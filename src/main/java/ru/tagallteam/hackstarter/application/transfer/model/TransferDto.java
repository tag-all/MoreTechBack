package ru.tagallteam.hackstarter.application.transfer.model;

import lombok.Data;
import ru.tagallteam.hackstarter.integration.modal.Status;

@Data
public class TransferDto {
    private Long userFrom;
    private Long userTo;
    private Status status;
    private String txHash;
}
