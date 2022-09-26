package ru.tagallteam.hackstarter.outsystem.service;

import java.util.Map;

public interface OutSystemRest<T> {
    /**
     * Get запрос с параметрами.
     *
     * @param url          добавочный url
     * @param requestParam параметры в виде ключ/значение
     * @param response тело ответа
     * @return моделька ответа.
     */
    <T> T getWithParam(String systemName, String url, Map<String, String> headerMap, Map<String, String> requestParam, Class<T> response);

    <T> T getWithOutParam(String systemName, String url, Map<String, String> headerMap , Class<T> response);

    <T> T post(String systemName, String url, Map<String, String> headerMap , Map<String, String> requestParam, Object body, Class<T> response);
}
