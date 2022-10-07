package ru.tagallteam.hackstarter.application.file.service.impl;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.tagallteam.hackstarter.application.file.domain.File;
import ru.tagallteam.hackstarter.application.file.domain.FileRepository;
import ru.tagallteam.hackstarter.application.file.mapper.FileMapper;
import ru.tagallteam.hackstarter.application.file.service.FileService;
import ru.tagallteam.hackstarter.errors.ErrorDescriptor;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileService;

    private final FileMapper fileMapper;

    @Override
    public ResponseEntity<Resource> getFile(UUID uuid) {
        File file = fileService.getByUuid(String.valueOf(uuid))
                .orElseThrow(ErrorDescriptor.FILE_NOT_FOUND::applicationException);
        return fileMapper.createResponseEntity(file);
    }

}
