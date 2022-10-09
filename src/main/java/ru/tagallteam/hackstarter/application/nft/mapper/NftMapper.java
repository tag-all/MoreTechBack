package ru.tagallteam.hackstarter.application.nft.mapper;

import org.springframework.stereotype.Component;
import ru.tagallteam.hackstarter.application.nft.domain.Nft;
import ru.tagallteam.hackstarter.application.nft.model.NftDto;
import ru.tagallteam.hackstarter.application.nft.model.NftUserDto;

@Component

public class NftMapper {

    public NftDto convertToNftDto(Nft nft, String uri){
        NftDto nftDto = new NftDto();
        nftDto.setName(nft.getName());
        nftDto.setTxHash(nft.getTxHash());
        nftDto.setPrice(nft.getPrice());
        nftDto.setClanId(nft.getClan().getId());
        nftDto.setUri(uri);
        return nftDto;
    }

    public NftUserDto convertToNftUserDto(Nft nft, String uri){
        NftUserDto nftDto = new NftUserDto();
        nftDto.setName(nft.getName());
        nftDto.setTxHash(nft.getTxHash());
        nftDto.setPrice(nft.getPrice());
        nftDto.setClanId(nft.getClan().getId());
        nftDto.setUri(uri);
        return nftDto;
    }
}
