package ru.tagallteam.hackstarter.outsystem.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.tagallteam.hackstarter.outsystem.model.SystemData;

import java.util.List;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "out-system")
public class ConfigurationOutSystem {
    private List<SystemData> systems;
}
