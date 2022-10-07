package ru.tagallteam.hackstarter.application.achievement.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class AchievementDto {

    private Long id;

    private Long fileId;

    private String name;

    private LocalDateTime date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AchievementDto that = (AchievementDto) o;
        return Objects.equals(id, that.id) && Objects.equals(fileId, that.fileId) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileId, name);
    }
}
