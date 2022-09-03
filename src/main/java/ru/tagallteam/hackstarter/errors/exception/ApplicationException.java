package ru.tagallteam.hackstarter.errors.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.tagallteam.hackstarter.errors.model.ApplicationError;

@Getter
@AllArgsConstructor(staticName = "of")
public class ApplicationException extends RuntimeException {
    private ApplicationError error;
}
