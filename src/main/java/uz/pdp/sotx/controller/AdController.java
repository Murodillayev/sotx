package uz.pdp.sotx.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sotx.exception.NotFoundException;
import uz.pdp.sotx.model.dto.AdCreateDto;
import uz.pdp.sotx.model.dto.AdDto;
import uz.pdp.sotx.model.dto.AdUpdateDto;
import uz.pdp.sotx.model.dto.ErrorDto;
import uz.pdp.sotx.service.AdService;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/ad")
@RequiredArgsConstructor
@RestController
public class AdController {

    private final AdService service;

    @PostMapping
    public AdDto create(@RequestBody AdCreateDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<AdDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AdDto get(@PathVariable String id) {
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public AdDto update(@PathVariable String id, @RequestBody AdUpdateDto dto) {
        return service.update(id, dto);
    }



}
