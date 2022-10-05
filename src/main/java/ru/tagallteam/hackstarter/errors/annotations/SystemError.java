package ru.tagallteam.hackstarter.errors.annotations;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ApiResponses(value = {
        @ApiResponse(code = 400, message = "Возникла ошибка при работе с данными запроса",
                response = ApiResponse.class,
                examples = @Example(value = @ExampleProperty(mediaType = "application/json",
                        value = "{\n\"errorType\": \"Тип ошибки\", \n" +
                                "  \"error\": \"Сообщение об ошибке\", \n" +
                                "  \"time\": \"Время ошибки\",\n" +
                                "  \"status\": \"Статус ошибки\"\n}")
                )),
        @ApiResponse(code = 500, message = "Неожиданная ошибка сервера",
                response = ApiResponse.class,
                examples = @Example(value = @ExampleProperty(mediaType = "application/json",
                        value = "{\n\"errorType\": \"Тип ошибки\", \n" +
                                "  \"error\": \"Сообщение об ошибке\", \n" +
                                "  \"time\": \"Время ошибки\",\n" +
                                "  \"status\": \"Статус ошибки\"\n}")
                )),
        @ApiResponse(code = 404, message = "Обработчик не найден",
                response = ApiResponse.class,
                examples = @Example(value = @ExampleProperty(mediaType = "application/json",
                        value = "{\n\"errorType\": \"Тип ошибки\", \n" +
                                "  \"error\": \"Сообщение об ошибке\", \n" +
                                "  \"time\": \"Время ошибки\",\n" +
                                "  \"status\": \"Статус ошибки\"\n}")
                ))
})
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemError {
}
