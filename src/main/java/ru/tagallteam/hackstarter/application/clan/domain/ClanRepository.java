package ru.tagallteam.hackstarter.application.clan.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tagallteam.hackstarter.application.nft.domain.Nft;
import ru.tagallteam.hackstarter.application.user.domain.User;

import java.util.List;

public interface ClanRepository extends JpaRepository<Clan, Long> {

    List<Clan> findAllByUsersIn(List<User> users);

    Clan findByNftsIn(List<Nft> nfts);

}
