package ru.tagallteam.hackstarter.application.nft.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NftUserDto extends NftDto {
    private String owner;
}
