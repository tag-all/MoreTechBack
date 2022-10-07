package ru.tagallteam.hackstarter.application.file.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> getByUuid(String uuid);
}
