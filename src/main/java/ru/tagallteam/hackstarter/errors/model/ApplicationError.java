package ru.tagallteam.hackstarter.errors.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.annotation.ApplicationScope;

import java.time.LocalDateTime;

/**
 * Ошибка приложения.
 *
 * @author Iurii Babalin.
 */
@Data
@AllArgsConstructor(staticName = "of")
public class ApplicationError {
    /**
     * Тип ошибки приложения
     */
    private ErrorType errorType;

    /**
     * Сообщение об ошибке.
     */
    private String error;

    /**
     * Время ошибки.
     */
    private LocalDateTime time;

    /**
     * Стутус, который возвращается при вызове ошибки.
     */
    private HttpStatus status;

    /**
     * Создание ошибки.
     *
     * @param error     текст ошибки.
     * @param errorType тип ошиби.
     * @param status    статус ошибки.
     * @return класс ошибки.
     */
    public ApplicationError (String error, ErrorType errorType, HttpStatus status) {
        this.error = error;
        this.errorType = errorType;
        this.status = status;
        this.time = LocalDateTime.now();
    }
}
