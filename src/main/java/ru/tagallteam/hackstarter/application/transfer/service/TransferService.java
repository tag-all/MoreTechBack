package ru.tagallteam.hackstarter.application.transfer.service;

import ru.tagallteam.hackstarter.application.transfer.model.TransferCreateModel;
import ru.tagallteam.hackstarter.application.transfer.model.TransferDto;
import ru.tagallteam.hackstarter.integration.modal.Status;

public interface TransferService {
    void transfer(Long userId, TransferCreateModel transfer);

    Status transferStatus(String txHash);

    TransferDto transferModel(String txHash);
}
