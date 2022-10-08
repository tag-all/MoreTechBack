package ru.tagallteam.hackstarter.application.nft.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NftRepository extends JpaRepository<Nft, Long> {
}
