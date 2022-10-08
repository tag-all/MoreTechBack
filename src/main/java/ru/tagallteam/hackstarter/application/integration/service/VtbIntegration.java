package ru.tagallteam.hackstarter.application.integration.service;

import ru.tagallteam.hackstarter.application.integration.modal.TransactionDto;
import ru.tagallteam.hackstarter.application.integration.modal.WalletDto;

public interface VtbIntegration {

    WalletDto createWallet();

    TransactionDto sendMatic();

    TransactionDto sendRuble();

    TransactionDto sendNft();

}
