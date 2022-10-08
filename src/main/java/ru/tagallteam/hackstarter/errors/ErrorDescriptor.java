package ru.tagallteam.hackstarter.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ru.tagallteam.hackstarter.errors.exception.ApplicationException;
import ru.tagallteam.hackstarter.errors.model.ApplicationError;
import ru.tagallteam.hackstarter.errors.model.ErrorType;

@Getter
@AllArgsConstructor
public enum ErrorDescriptor {
    TRANSFER_NOT_FOUND("Транзакция не найден", ErrorType.APP, HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("Пользователь н" +
            "е найден", ErrorType.APP, HttpStatus.NOT_FOUND),
    CLAN_NOT_FOUND("Сообщество не найдено", ErrorType.APP, HttpStatus.NOT_FOUND),
    USER_IN_EVENT("Вы уже записаны на метроприятие", ErrorType.APP, HttpStatus.BAD_REQUEST),
    FILE_NOT_FOUND("Файл не найден", ErrorType.APP, HttpStatus.NOT_FOUND),
    USER_TOKEN_ACCESS_NOT_FOUND("Токен доступа с заданным идентификатором не найден", ErrorType.APP,
            HttpStatus.NOT_FOUND),
    EVENT_NOT_PUBLIC("Данное мероприятие находится на валидиции, вы не можете принять в нём участие",
            ErrorType.APP, HttpStatus.BAD_REQUEST),
    USER_LOGOUT_LAST("Пользователь уже вышел", ErrorType.APP, HttpStatus.BAD_REQUEST),
    USER_IS_CREATED("Пользователь уже существует", ErrorType.APP, HttpStatus.BAD_REQUEST),
    USER_AUTH_PROBLEM("Логин или пароль неверный", ErrorType.APP, HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_ACCESS("Неавторизованный доступ", ErrorType.APP, HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED("Недостаточно прав для доступа к ресурсу", ErrorType.APP, HttpStatus.FORBIDDEN),
    NOT_FOUND("Запрошенный ресурс (интерфейс) не существует", ErrorType.APP, HttpStatus.NOT_FOUND),
    OUT_SYSTEM_ERROR_IN_URL("Ошибка во время обработки URL внешнего сервиса", ErrorType.OUT_SYSTEM, HttpStatus.INTERNAL_SERVER_ERROR),
    PRODUCT_OUT_OF_STOCK("Товар закончился!", ErrorType.APP, HttpStatus.BAD_REQUEST),
    EVENT_NOT_FOUND("Мероприятие не найдено", ErrorType.APP, HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND("Товар не найден", ErrorType.APP, HttpStatus.NOT_FOUND);

    private final String message;

    private final ErrorType type;

    private final HttpStatus status;

    public void throwApplicationException() {
        throw ApplicationException.of(applicationError());
    }

    public ApplicationException applicationException() {
        return ApplicationException.of(applicationError());
    }

    public void throwIsTrue(Boolean flag) {
        if (flag) {
            throwApplicationException();
        }
    }

    public void throwIsFalse(Boolean flag) {
        if (!flag) {
            throwApplicationException();
        }
    }

    public ApplicationError applicationError() {
        return new ApplicationError(this.message, this.type, this.status);
    }

}
