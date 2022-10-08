package ru.tagallteam.hackstarter.integration.modal;

import lombok.Data;

@Data
public class HistoryItem {
    private Long blockNumber;
    private Long timeStamp;
    private String contractAddress;
    private String from;
    private String to;
    private Long value;
    private String tokenName;
    private String tokenSymbol;
    private Long gasUsed;
    private Long confirmations;
}
