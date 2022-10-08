package ru.tagallteam.hackstarter.application.clan.service;

import ru.tagallteam.hackstarter.application.clan.model.ClanDto;

import java.util.List;

public interface ClanService {

    List<ClanDto> getAllClans();

    List<ClanDto> getAllClansForUser(Long user_id);

    ClanDto getClanForNFT(Long nft_id);

    void addUserToClan(Long clan_id, Long user_id);

}
