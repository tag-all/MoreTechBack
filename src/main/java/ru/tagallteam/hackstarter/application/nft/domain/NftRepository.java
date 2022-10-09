package ru.tagallteam.hackstarter.application.nft.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftRepository extends JpaRepository<Nft, Long> {

    Optional<Nft> getNftByTxHash(String txHash);
}
