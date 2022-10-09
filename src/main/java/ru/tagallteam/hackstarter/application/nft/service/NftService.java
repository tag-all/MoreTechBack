package ru.tagallteam.hackstarter.application.nft.service;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import ru.tagallteam.hackstarter.application.nft.model.NftDto;
import ru.tagallteam.hackstarter.application.nft.model.NftUserDto;

public interface NftService {
    List<NftDto> nftUser(Long userId);

    NftUserDto nftWithUser(Long tokenId);
}
