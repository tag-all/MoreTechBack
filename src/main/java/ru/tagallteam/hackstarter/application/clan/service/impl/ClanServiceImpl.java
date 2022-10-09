package ru.tagallteam.hackstarter.application.clan.service.impl;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tagallteam.hackstarter.application.achievement.service.AchievementService;
import ru.tagallteam.hackstarter.application.clan.domain.Clan;
import ru.tagallteam.hackstarter.application.clan.domain.ClanRepository;
import ru.tagallteam.hackstarter.application.clan.mapper.ClanMapper;
import ru.tagallteam.hackstarter.application.clan.model.ClanDto;
import ru.tagallteam.hackstarter.application.clan.service.ClanService;
import ru.tagallteam.hackstarter.application.nft.domain.Nft;
import ru.tagallteam.hackstarter.application.nft.domain.NftRepository;
import ru.tagallteam.hackstarter.application.user.domain.User;
import ru.tagallteam.hackstarter.application.user.domain.UserRepository;
import ru.tagallteam.hackstarter.application.user.model.ProfileDto;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;
import ru.tagallteam.hackstarter.integration.modal.NftCreate;
import ru.tagallteam.hackstarter.integration.modal.NftCreateTransaction;
import ru.tagallteam.hackstarter.integration.modal.NftGenerateInfo;
import ru.tagallteam.hackstarter.integration.service.VtbIntegration;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.SECONDS;

@Service
@Slf4j
@AllArgsConstructor
public class ClanServiceImpl implements ClanService {

    private final ClanRepository clanRepository;
    private final UserRepository userRepository;
    private final NftRepository nftRepository;
    private final ClanMapper clanMapper;

    private final VtbIntegration integration;
    private final AchievementService achievementService;

    @Override
    public List<ClanDto> getAllClans() {
        return clanRepository.findAll()
                .stream()
                .map(clanMapper::convertToClanDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClanDto> getAllClansForUser(Long user_id) {
        User user = userRepository.findById(user_id)
                .orElseThrow(ErrorDescriptor.USER_NOT_FOUND::applicationException);
        List<Clan> userClans = clanRepository.findAllByUsersIn(Collections.singletonList(user));
        return userClans.stream()
                .map(clanMapper::convertToClanDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClanDto getClanForNFT(Long nft_id) {
        Nft nft = nftRepository.findById(nft_id)
                .orElseThrow(ErrorDescriptor.USER_NOT_FOUND::applicationException);
        Clan nftClan = clanRepository.findByNftsIn(Collections.singletonList(nft));
        return clanMapper.convertToClanDto(nftClan);
    }

    @SneakyThrows
    @Override
    @Transactional
    public void addUserToClan(Long clan_id) {
        ProfileDto profileDto = (ProfileDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.getUserByEmail(profileDto.getEmail())
                .orElseThrow(ErrorDescriptor.USER_NOT_FOUND::applicationException);
        Clan clan = clanRepository.findById(clan_id)
                .orElseThrow(ErrorDescriptor.CLAN_NOT_FOUND::applicationException);
        NftCreate nftCreate = NftCreate.builder()
                .toPublicKey(user.getPublicKey())
                .uri(clan.getStart_img())
                .nftCount(1L).build();
        NftCreateTransaction transaction = integration.generateNft(nftCreate);
        Thread.sleep(SECONDS.toMillis(20));
        NftGenerateInfo nftGenerateInfo = integration.getGenerateNft(transaction.getTransactionHash());
        Nft nft = new Nft();
        nft.setUser(user);
        nft.setName(clan.getName().concat(" #").concat(String.valueOf(nftGenerateInfo.getTokens().get(0))));
        nft.setTxHash(String.valueOf(nftGenerateInfo.getTokens().get(0)));
        nft.setPrice(1L);
        clan.getUsers().add(user);
        clan = clanRepository.save(clan);
        nft.setClan(clan);
        nftRepository.save(nft);

        achievementService.addAchievementToUser(user.getId(), 9L);
    }

}
