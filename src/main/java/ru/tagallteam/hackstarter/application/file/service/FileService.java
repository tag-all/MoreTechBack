package ru.tagallteam.hackstarter.application.file.service;

import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface FileService {
    ResponseEntity<Resource> getFile(UUID uuid);
}
