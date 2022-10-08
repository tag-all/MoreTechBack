package ru.tagallteam.hackstarter.integration.modal;

import java.util.List;
import lombok.Data;

@Data
public class WalletHistory {
    private List<HistoryItem> history;
}
