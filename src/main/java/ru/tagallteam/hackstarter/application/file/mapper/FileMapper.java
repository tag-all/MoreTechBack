package ru.tagallteam.hackstarter.application.file.mapper;

import java.util.Base64;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.tagallteam.hackstarter.application.file.domain.File;

@Component
public class FileMapper {
    public ResponseEntity<Resource> createResponseEntity(File file) {
        Resource resource = convertBase64ToResource(file.getData());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, file.getContentType());
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Content-Disposition");

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    private static Resource convertBase64ToResource(String base64Content) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64Content);
        return new ByteArrayResource(decodedBytes);
    }
}
