package uz.pdp.sotx.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.sotx.model.entity.Image;
import uz.pdp.sotx.repository.ImageRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileService {

    private final ImageRepository repository;

    public ResponseEntity<byte[]> getImageBytes(String id) {

        Image fileEntity = repository.findById(id).orElseThrow(() -> new RuntimeException("Invalid file id"));

        return null;
    }
}
