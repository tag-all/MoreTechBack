package ru.tagallteam.hackstarter.application.file.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.tagallteam.hackstarter.application.common.Endpoints;
import ru.tagallteam.hackstarter.application.file.service.FileService;
import ru.tagallteam.hackstarter.errors.annotations.SystemError;

@Api(tags = "Работа с файлами")
@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @ApiOperation(value = "Полученеи файла",
            notes = "Получение файла из бд")
    @SystemError
    @GetMapping(Endpoints.FileService.FILE)
    public ResponseEntity<Resource> getFile(@PathVariable UUID uuid) {
        return fileService.getFile(uuid);
    }
}
