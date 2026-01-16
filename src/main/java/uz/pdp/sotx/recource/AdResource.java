package uz.pdp.sotx.recource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sotx.criteria.AdCriteria;
import uz.pdp.sotx.model.dto.AdCreateDto;
import uz.pdp.sotx.model.dto.AdDto;
import uz.pdp.sotx.model.dto.AdUpdateDto;
import uz.pdp.sotx.model.dto.PageableDto;
import uz.pdp.sotx.model.enums.Category;
import uz.pdp.sotx.service.AdService;

import java.awt.*;
import java.util.List;

@RequestMapping("/api/v1/ad")
@RequiredArgsConstructor
@RestController
public class AdResource {

    private final AdService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AdDto create(@ModelAttribute AdCreateDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public PageableDto<List<AdDto>> getAll(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "0") Integer page

    ) {
        AdCriteria criteria = AdCriteria.builder()
                .search(search)
                .size(size)
                .page(page)
                .build();


        criteria.setCategory((category == null || category.isEmpty()) ? null : Category.valueOf(category));

        return service.getAll(criteria);
    }

    @GetMapping("/my")
    public List<AdDto> getAllMy(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "0") Integer page
    ) {
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
