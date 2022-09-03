package ru.tagallteam.hackstarter.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import ru.tagallteam.hackstarter.errors.exception.ApplicationException;
import ru.tagallteam.hackstarter.errors.model.ApplicationError;
import ru.tagallteam.hackstarter.errors.model.ErrorType;

@Getter
@AllArgsConstructor
public enum ErrorDescriptor {
    NOT_FOUND("Запрошенный ресурс (интерфейс) не существует", ErrorType.APP, HttpStatus.NOT_FOUND);


    private final String message;

    private final ErrorType type;

    private final HttpStatus status;

    public void exception() {
        throw ApplicationException.of(applicationError());
    }

    public void throwIsTrue(Boolean flag) {
        if (flag) {
            exception();
        }
    }

    public void throwIsFalse(Boolean flag) {
        if (!flag) {
            exception();
        }
    }

    public void throwIsNull(Object object) {
        if (ObjectUtils.isEmpty(object)) {
            exception();
        }
    }

    public ApplicationError applicationError() {
        return new ApplicationError(this.message, this.type, this.status);
    }

}
