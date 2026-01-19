package uz.pdp.sotx.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.pdp.sotx.criteria.AdCriteria;
import uz.pdp.sotx.mapper.AdMapper;
import uz.pdp.sotx.model.dto.AdCreateDto;
import uz.pdp.sotx.model.dto.AdDto;
import uz.pdp.sotx.model.dto.AdUpdateDto;
import uz.pdp.sotx.model.dto.PageableDto;
import uz.pdp.sotx.model.entity.Ad;
import uz.pdp.sotx.repository.AdRepository;
import uz.pdp.sotx.validator.AdValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdService
        implements CrudService<AdCreateDto, AdUpdateDto, AdDto, String, AdCriteria> {
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
    public PageableDto<List<AdDto>> getAll(AdCriteria criteria) {

        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(Sort.Direction.DESC, "created_at"));


        Page<Ad> page = repository.findAllByCriteria(criteria.getSearch(), criteria.getCategory() == null ? null : criteria.getCategory().name(), pageable);

        List<AdDto> ads = mapper.toDto(page.getContent());

        return new PageableDto<>(ads, page.getTotalPages(), page.getTotalElements());
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
