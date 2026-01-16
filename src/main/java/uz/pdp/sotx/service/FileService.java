package uz.pdp.sotx.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.sotx.model.entity.Image;
import uz.pdp.sotx.repository.ImageRepository;

@Service
@RequiredArgsConstructor
public class FileService {

    private final ImageRepository repository;

    public ResponseEntity<byte[]> getImageBytes(String id) {

        Image fileEntity = repository.findById(id).orElseThrow(() -> new RuntimeException("Invalid file id"));

        return null;
    }
}
