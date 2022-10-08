package ru.tagallteam.hackstarter.application.user.model;

import lombok.Data;
import ru.tagallteam.hackstarter.application.achievement.model.AchievementDto;
import ru.tagallteam.hackstarter.application.activity.model.ActivityDto;
import ru.tagallteam.hackstarter.application.event.modal.EventDto;
import ru.tagallteam.hackstarter.application.transfer.model.TransferDto;
import ru.tagallteam.hackstarter.application.user.domain.UserRole;

import java.util.List;
import ru.tagallteam.hackstarter.integration.modal.BalanceNtfWallet;
import ru.tagallteam.hackstarter.integration.modal.BalanceWallet;

@Data
public class ProfileDto {
    private Long id;
    private UserRole role;
    private String name;
    private String lastName;
    private Long lvl;
    private Long xp;
    private BalanceWallet balance;
    private BalanceNtfWallet balanceNft;
    private String email;
    private List<AchievementDto> achievements;
    private List<ActivityDto> activities;
    private List<EventDto> events;
    private List<TransferDto> transfersFrom;
    private List<TransferDto> transfersTo;
}
