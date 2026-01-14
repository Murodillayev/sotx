package uz.pdp.sotx.recource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.sotx.service.FileService;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileResource {

    private final FileService service;

    @GetMapping("/{fileId}")
    public ResponseEntity<byte[]> download(@PathVariable String fileId) {
        return service.getImageBytes(fileId);
    }
}


// http://localhost:8080/file/ + imageId
// http://localhost:8080/file/ + imageId
// http://localhost:8080/file/ + imageId
// http://localhost:8080/file/ + imageId
// http://localhost:8080/file/ + imageId