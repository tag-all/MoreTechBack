package ru.tagallteam.hackstarter.integration.system;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemRequest {
    VTB("VTB");

    private final String name;
}
