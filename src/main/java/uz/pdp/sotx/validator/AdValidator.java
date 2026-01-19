package uz.pdp.sotx.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.sotx.exception.NotFoundException;
import uz.pdp.sotx.model.dto.AdCreateDto;
import uz.pdp.sotx.model.entity.Ad;
import uz.pdp.sotx.repository.AdRepository;

@Component
@RequiredArgsConstructor
public class AdValidator {
    private final AdRepository repository;
    public void validateOnCreate(AdCreateDto dto) {

    }

    public Ad existsAndGet(String id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Ad with id " + id + " not found!")
        );
    }
}
