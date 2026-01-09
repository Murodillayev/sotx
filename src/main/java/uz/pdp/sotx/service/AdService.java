package uz.pdp.sotx.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.sotx.mapper.AdMapper;
import uz.pdp.sotx.model.dto.AdCreateDto;
import uz.pdp.sotx.model.dto.AdDto;
import uz.pdp.sotx.model.dto.AdUpdateDto;
import uz.pdp.sotx.model.entity.Ad;
import uz.pdp.sotx.repository.AdRepository;
import uz.pdp.sotx.va.AdValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdService implements CrudService<AdCreateDto, AdUpdateDto, AdDto, String> {

    private final AdRepository repository;
    private final AdMapper mapper;
    private final AdValidator validator;

    @Override
    public void create(AdCreateDto dto) {
        validator.validateOnCreate(dto);
        Ad ad = mapper.fromDto(dto);
        repository.save(ad);
    }

    @Override
    public void update(String id, AdUpdateDto dto) {
        Ad ad = validator.existsAndGet(id);
        mapper.fromDto(ad, dto);
        repository.save(ad);
    }

    @Override
    public List<AdDto> getAll() {
        return List.of();
    }

    @Override
    public AdDto get(String s) {
        return null;
    }

    @Override
    public void delete(String s) {

    }
}
