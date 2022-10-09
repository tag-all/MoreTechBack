package ru.tagallteam.hackstarter.application.nft.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tagallteam.hackstarter.application.nft.mapper.NftMapper;
import ru.tagallteam.hackstarter.application.nft.model.NftDto;
import ru.tagallteam.hackstarter.application.nft.model.NftUserDto;
import ru.tagallteam.hackstarter.application.nft.service.NftService;
import ru.tagallteam.hackstarter.application.user.domain.User;
import ru.tagallteam.hackstarter.application.user.domain.UserRepository;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;
import ru.tagallteam.hackstarter.integration.modal.NftTokenDto;
import ru.tagallteam.hackstarter.integration.service.VtbIntegration;

@Slf4j
@Service
@RequiredArgsConstructor
public class NftServiceImpl implements NftService {

    private final UserRepository userRepository;

    private final NftMapper mapper;

    private final VtbIntegration integration;

    @Override
    public List<NftDto> nftUser(Long userId) {
        ErrorDescriptor.USER_NOT_FOUND.throwIsFalse(userRepository.existsById(userId));
        return userRepository.getById(userId).getNfts().stream()
                .map(it -> mapper.convertToNftDto(it, integration.getNftInfo(Long.valueOf(it.getTxHash())).getUri()))
                .collect(Collectors.toList());
    }

    @Override
    public NftUserDto nftWithUser(Long tokenId) {
        NftTokenDto nftTokenDto = integration.getNftInfo(tokenId);
        User user = userRepository.getUserByPublicKey(nftTokenDto.getPublicKey())
                .orElseThrow(ErrorDescriptor.USER_NOT_FOUND::applicationException);
        NftUserDto nftUserDto = (NftUserDto) mapper.convertToNftDto(user.getNfts().stream()
                .filter(it -> it.getTxHash().equals(tokenId.toString())).findFirst()
                .orElseThrow(ErrorDescriptor.NFT_NOT_FOUND::applicationException), nftTokenDto.getUri());
        nftUserDto.setOwner(user.getName().concat(" ").concat(user.getLastName()));
        return nftUserDto;
    }
}
