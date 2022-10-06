package ru.tagallteam.hackstarter.errors.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

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
    private String message;

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
     * @param message     текст ошибки.
     * @param errorType тип ошиби.
     * @param status    статус ошибки.
     * @return класс ошибки.
     */
    public ApplicationError(String message, ErrorType errorType, HttpStatus status) {
        this.message = message;
        this.errorType = errorType;
        this.status = status;
        this.time = LocalDateTime.now();
    }
}
