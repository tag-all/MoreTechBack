package ru.tagallteam.hackstarter.outsystem.config;

import lombok.val;
import org.springframework.stereotype.Component;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;

import java.util.Map;

@Component
public class UrlParser {

    public String getFullUrl(String url, Map<String, String> map) {
        val components = url.split("/");
        StringBuilder result = new StringBuilder();
        for (String component : components) {
            if (component.contains("{") || component.contains("}")) {
                String field = component.substring(1, component.length() - 1);
                if (map.containsKey(field)) {
                    result.append(map.get(field));
                    result.append("/");
                } else {
                    ErrorDescriptor.OUT_SYSTEM_ERROR_IN_URL.throwApplicationException();
                }
            } else {
                result.append(component);
                result.append("/");
            }
        }
        String urlResult = result.toString();
        return urlResult.substring(0, urlResult.length() - 1);
    }
}
