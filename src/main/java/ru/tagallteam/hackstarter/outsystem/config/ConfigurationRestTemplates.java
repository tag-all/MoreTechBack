package ru.tagallteam.hackstarter.outsystem.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import ru.tagallteam.hackstarter.outsystem.model.SystemTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Data
@Configuration
@RequiredArgsConstructor
public class ConfigurationRestTemplates {

    private final ConfigurationOutSystem configurationOutSystem;

    /**
     * Создание шаблона для запросов.
     *
     * @return шаблон.
     */
    @Bean
    public Map<String, SystemTemplate> outSystemRestTemplate() {
        Map<String, SystemTemplate> outSystems = new HashMap<>(Collections.emptyMap());
        configurationOutSystem.getSystems().forEach(it -> {
            SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
            clientHttpRequestFactory.setConnectTimeout(Math.toIntExact(it.getConnectTimeout()));
            clientHttpRequestFactory.setReadTimeout(Math.toIntExact(it.getReadTimeout()));
            outSystems.put(it.getName(), SystemTemplate.of(it, new RestTemplate(clientHttpRequestFactory)));
        });
        return outSystems;
    }
}
