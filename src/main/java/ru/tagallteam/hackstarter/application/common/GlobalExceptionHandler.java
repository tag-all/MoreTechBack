package ru.tagallteam.hackstarter.application.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;
import ru.tagallteam.hackstarter.errors.model.ApplicationError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Обработка исключения {@link NoHandlerFoundException}.
     *
     * @param ex исключение.
     * @return ошибка приложения.
     */
    @ResponseBody
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApplicationError handleError404(NoHandlerFoundException ex, HttpServletResponse response,
                                           HttpServletRequest request) {
        response.setStatus(ErrorDescriptor.NOT_FOUND.getStatus().value());
        return ErrorDescriptor.NOT_FOUND.applicationError();
    }


}
