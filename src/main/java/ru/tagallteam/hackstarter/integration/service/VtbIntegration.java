package ru.tagallteam.hackstarter.integration.service;

import ru.tagallteam.hackstarter.integration.modal.BalanceNtfWallet;
import ru.tagallteam.hackstarter.integration.modal.BalanceWallet;
import ru.tagallteam.hackstarter.integration.modal.FilterHistory;
import ru.tagallteam.hackstarter.integration.modal.NftGenerateInfo;
import ru.tagallteam.hackstarter.integration.modal.SendCurrency;
import ru.tagallteam.hackstarter.integration.modal.SendNft;
import ru.tagallteam.hackstarter.integration.modal.Status;
import ru.tagallteam.hackstarter.integration.modal.TransactionDto;
import ru.tagallteam.hackstarter.integration.modal.WalletDto;
import ru.tagallteam.hackstarter.integration.modal.WalletHistory;

public interface VtbIntegration {

    WalletDto createWallet();

    TransactionDto sendMatic(SendCurrency sendCurrency);

    TransactionDto sendRuble(SendCurrency sendCurrency);

    TransactionDto sendNft(SendNft sendNft);

    Status statusTransaction(String transferKey);

     BalanceWallet getBalanceWallet(String key);

    BalanceNtfWallet getNftBalanceWallet(String key);

    TransactionDto generateNft(SendNft sendNft);

    SendNft getGenerateNft();

    NftGenerateInfo getNftInfo();

    WalletHistory getWalletHistory(FilterHistory filter);
}
