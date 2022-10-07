package ru.tagallteam.hackstarter.application.event.modal;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.tagallteam.hackstarter.application.common.CommonAttributeMapper;

@RequiredArgsConstructor
public enum EventAttributeType {

    REWARD_TYPE("reward_type", new CommonAttributeMapper.StringCommonAttributeMapper()  ),
    NUMBER_AWARDS("number_awards", new CommonAttributeMapper.StringCommonAttributeMapper());


    @Schema(description = "Наименование")
    @Getter
    @JsonValue
    private final String name;

    @Schema(description = "Маппер атрибутов")
    private final CommonAttributeMapper<?> commonAttributeMapper;

    public Object getValue(String rawValue) {
        return commonAttributeMapper.valueOf(rawValue);
    }

    public String fromValue(Object value) {
        return commonAttributeMapper.fromValue(value);
    }
}
