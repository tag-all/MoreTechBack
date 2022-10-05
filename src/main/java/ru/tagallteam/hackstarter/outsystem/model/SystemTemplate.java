package ru.tagallteam.hackstarter.outsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.client.RestTemplate;

@Data
@AllArgsConstructor(staticName = "of")
public class SystemTemplate {
    private SystemData system;
    private RestTemplate template;
}
