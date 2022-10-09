package ru.tagallteam.hackstarter.application.common.handler;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;
import ru.tagallteam.hackstarter.errors.exception.ApplicationException;
import ru.tagallteam.hackstarter.errors.model.ApplicationError;
import ru.tagallteam.hackstarter.errors.model.ErrorType;


/**
 * Обработка ошибок приложения.
 *
 * @author Iurii Babalin.
 */
@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Обработка исключения {@link ApplicationException}.
     *
     * @param ex исключение.
     * @return ошибка приложения.
     */
    @ResponseBody
    @ExceptionHandler(ApplicationException.class)
    public ApplicationError applicationException(ApplicationException ex, HttpServletResponse response) {
        response.setStatus(ex.getError().getStatus().value());
        return ex.getError();
    }

    @ResponseBody
    @ExceptionHandler(HttpServerErrorException.class)
    public ApplicationError outSystemException(Exception ex, HttpServletResponse response){
        log.info(ex.getMessage());
        response.setStatus(ErrorDescriptor.OUT_SYSTEM_ERROR_IN_URL.applicationError().getStatus().value());
        return ErrorDescriptor.OUT_SYSTEM_ERROR_IN_URL.applicationError();
    }
}
