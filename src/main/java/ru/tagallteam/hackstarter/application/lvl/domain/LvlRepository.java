package ru.tagallteam.hackstarter.application.lvl.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LvlRepository extends JpaRepository<Lvl, Long> {

    Optional<Lvl> findLvlByName(String name);

}
