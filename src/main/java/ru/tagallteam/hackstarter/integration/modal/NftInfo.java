package ru.tagallteam.hackstarter.integration.modal;

import java.util.List;
import lombok.Data;

@Data
public class NftInfo {
    public String uri;
    public List<Long> tokens;
}
