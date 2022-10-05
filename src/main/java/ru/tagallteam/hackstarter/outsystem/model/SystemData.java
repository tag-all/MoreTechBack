package ru.tagallteam.hackstarter.outsystem.model;

import lombok.Data;

/**
 * Модель для получения данных о внешней системе.
 *
 * @author Iurii Babalin.
 */
@Data
public class SystemData {
    /**
     * Название системы.
     */
    private String name;
    /**
     * Ссылка на внешнюю систему.
     */
    private String systemLink;
    /**
     * Ключ доступа.
     */
    private String systemKey;
    /**
     * Нужно ли добавлять Bearer.
     */
    private Boolean bearer;
    /**
     * Время подключения.
     */
    private Long connectTimeout = 30000L;
    /**
     * Время ожидания ответа.
     */
    private Long readTimeout = 30000L;
}
