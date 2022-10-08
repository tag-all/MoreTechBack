package ru.tagallteam.hackstarter.application.clan.mapper;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import ru.tagallteam.hackstarter.application.activity.domain.Activity;
import ru.tagallteam.hackstarter.application.activity.model.ActivityDto;
import ru.tagallteam.hackstarter.application.activity.model.ActivityType;
import ru.tagallteam.hackstarter.application.clan.domain.Clan;
import ru.tagallteam.hackstarter.application.clan.model.ClanDto;

@Component
public class ClanMapper {

    public ClanDto convertToClanDto(Clan clan) {
        ClanDto clanDto = new ClanDto();
        clanDto.setId(clan.getId());
        clanDto.setName(clan.getName());
        clanDto.setStart_img(clan.getStart_img());
        return clanDto;
    }


}
