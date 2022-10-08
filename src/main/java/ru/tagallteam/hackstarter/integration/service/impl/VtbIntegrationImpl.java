package ru.tagallteam.hackstarter.integration.service.impl;

import java.util.Collections;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tagallteam.hackstarter.integration.Endpoints;
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
import ru.tagallteam.hackstarter.integration.service.VtbIntegration;
import ru.tagallteam.hackstarter.integration.system.SystemRequest;
import ru.tagallteam.hackstarter.outsystem.service.OutSystemRest;
import ru.tagallteam.hackstarter.outsystem.utils.UrlParser;

@Service
@RequiredArgsConstructor
public class VtbIntegrationImpl implements VtbIntegration {

    private final OutSystemRest<Object> outSystemRest;

    private final UrlParser urlParser;

    @Override
    public WalletDto createWallet() {
        return outSystemRest.post(SystemRequest.VTB.getName(), Endpoints.CREATE_WALLET,
                Collections.emptyMap(), Collections.emptyMap(), null, WalletDto.class);
    }

    @Override
    public TransactionDto sendMatic(SendCurrency sendCurrency) {
        return outSystemRest.post(SystemRequest.VTB.getName(), Endpoints.TRANSFER_MATIC,
                Collections.emptyMap(), Collections.emptyMap(), sendCurrency, TransactionDto.class);
    }

    @Override
    public TransactionDto sendRuble(SendCurrency sendCurrency) {
        return outSystemRest.post(SystemRequest.VTB.getName(), Endpoints.TRANSFER_RUBLE,
                Collections.emptyMap(), Collections.emptyMap(), sendCurrency, TransactionDto.class);
    }

    @Override
    public TransactionDto sendNft(SendNft sendNft) {
        return outSystemRest.post(SystemRequest.VTB.getName(), Endpoints.TRANSFER_NFT,
                Collections.emptyMap(), Collections.emptyMap(), sendNft, TransactionDto.class);
    }

    @Override
    public Status statusTransaction(String transferKey) {
        String url = urlParser.getFullUrl(Endpoints.TRANSFER_STATUS, Map.of("transactionHash", transferKey));
        return outSystemRest.getWithOutParam(SystemRequest.VTB.getName(), url, Collections.emptyMap(), Status.class);
    }

    @Override
    public BalanceWallet getBalanceWallet(String key) {
        String url = urlParser.getFullUrl(Endpoints.WALLET_BALANCE, Map.of("publicKey", key));
        return outSystemRest.getWithOutParam(SystemRequest.VTB.getName(), url, Collections.emptyMap(),
                BalanceWallet.class);
    }

    @Override
    public BalanceNtfWallet getNftBalanceWallet(String key) {
        String url = urlParser.getFullUrl(Endpoints.WALLET_NFT_BALANCE, Map.of("publicKey", key));
        return outSystemRest.getWithOutParam(SystemRequest.VTB.getName(), url, Collections.emptyMap(),
                BalanceNtfWallet.class);
    }

    @Override
    public TransactionDto generateNft(SendNft sendNft) {
        return null;
    }

    @Override
    public SendNft getGenerateNft() {
        return null;
    }

    @Override
    public NftGenerateInfo getNftInfo() {
        return null;
    }

    @Override
    public WalletHistory getWalletHistory(FilterHistory filter) {
        return null;
    }
}
