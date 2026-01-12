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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdService implements CrudService<AdCreateDto, AdUpdateDto, AdDto, String> {

    private final AdRepository repository;
    private final AdMapper mapper;
    private final AdValidator validator;

    @Override
    public AdDto create(AdCreateDto dto) {
        validator.validateOnCreate(dto);
        Ad ad = mapper.fromDto(dto);  // dto -> entity
        Ad save = repository.save(ad);
        return mapper.toDto(save);
    }

    @Override
    public AdDto update(String id, AdUpdateDto dto) {
        Ad ad = validator.existsAndGet(id);
        mapper.fromDto(ad, dto);
        return mapper.toDto(repository.save(ad));
    }

    @Override
    public List<AdDto> getAll() {
        List<Ad> ads = repository.findAll();
        return mapper.toDto(ads);
    }

    @Override
    public AdDto get(String id) {
        Ad ad = validator.existsAndGet(id);
        return mapper.toDto(ad);
    }

    @Override
    public void delete(String id) {
        Ad ad = validator.existsAndGet(id);
        ad.setDeleted(true);
        repository.save(ad);
    }

}
