package ru.tagallteam.hackstarter.integration;

public interface Endpoints {

    String CREATE_WALLET = "/v1/wallets/new";

    String TRANSFER_MATIC = "/v1/transfers/matic";

    String TRANSFER_RUBLE = "/v1/transfers/ruble";

    String TRANSFER_NFT = "v1/transfers/nft";

    String TRANSFER_STATUS = "/v1/transfers/status/{transactionHash}";

    String WALLET_BALANCE = "/v1/wallets/{publicKey}/balance";

    String WALLET_NFT_BALANCE = "/v1/wallets/{publicKey}/nft/balance";

    String NFT_COLLECTION_GENERATE = "/v1/nft/generate";

    String LIST_NFT_COLLECTION = "/v1/nft/generate/{transactionHash}";

    String NFT_INFO = "/v1/nft/{tokenId}";

    String WALLET_HISTORY = "/v1/wallets/{publicKey}/history";

}
