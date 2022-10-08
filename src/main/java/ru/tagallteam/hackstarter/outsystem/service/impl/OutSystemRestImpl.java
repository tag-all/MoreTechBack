package ru.tagallteam.hackstarter.outsystem.service.impl;

import java.util.Collections;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import ru.tagallteam.hackstarter.outsystem.model.SystemTemplate;
import ru.tagallteam.hackstarter.outsystem.service.OutSystemRest;

@Service
@RequiredArgsConstructor
public class OutSystemRestImpl<T> implements OutSystemRest<T> {

    private final Map<String, SystemTemplate> outSystemRestTemplate;

    @Override
    public <T> T getWithParam(String systemName, String url, Map<String, String> headerMap, Map<String, String> requestParam, Class<T> response) {
        SystemTemplate systemTemplate = outSystemRestTemplate.get(systemName);
        String requestUrl = systemTemplate.getSystem().getSystemLink().concat(url.concat(getPathParamInMap(requestParam)));
        return systemTemplate.getTemplate().getForObject(requestUrl, response);
    }

    @Override
    public <T> T getWithOutParam(String systemName, String url, Map<String, String> headerMap, Class<T> response) {
        SystemTemplate systemTemplate = outSystemRestTemplate.get(systemName);
        return systemTemplate.getTemplate().getForObject(systemTemplate.getSystem().getSystemLink().concat(url), response);
    }

    @Override
    public <T1> T1 post(String systemName, String url, Map<String, String> headerMap, Map<String, String> requestParam,
                        Object body, Class<T1> response) {
        SystemTemplate systemTemplate = outSystemRestTemplate.get(systemName);
        String requestUrl = systemTemplate.getSystem().getSystemLink().concat(url.concat(getPathParamInMap(requestParam)));
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headerMap.forEach(headers::set);
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        return systemTemplate.getTemplate().postForObject(requestUrl, entity, response);
    }

    private String getPathParamInMap(Map<String, String> requestParam) {
        StringBuilder requestUrl = new StringBuilder();
        requestParam.keySet().forEach(it -> requestUrl.append(it).append("=").append(requestParam.get(it)).append("&"));
        return requestUrl.isEmpty() ? "" : "?".concat(requestUrl.substring(0, requestUrl.length() - 1));
    }

}
