package ru.tagallteam.hackstarter.application.transfer.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    boolean existsByKey(String key);

    Optional<Transfer> getByKey(String key);
}
