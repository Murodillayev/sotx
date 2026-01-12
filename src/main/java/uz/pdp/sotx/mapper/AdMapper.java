package uz.pdp.sotx.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.sotx.model.dto.AdCreateDto;
import uz.pdp.sotx.model.dto.AdDto;
import uz.pdp.sotx.model.dto.AdUpdateDto;
import uz.pdp.sotx.model.entity.Ad;
import uz.pdp.sotx.model.enums.Currency;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdMapper {

    public Ad fromDto(AdCreateDto dto) {
        Ad ad = new Ad();
        ad.setCategory(dto.getCategory());
        ad.setTitle(dto.getTitle());
        ad.setDescription(dto.getDescription());
        ad.setCreatedAt(LocalDateTime.now());
        ad.setUpdatedAt(LocalDateTime.now());
        ad.setCreatedBy("system");
        ad.setUpdatedBy("system");
        ad.setCurrency(dto.getCurrency());
        ad.setPrice(dto.getPrice());
        return ad;
    }

    public void fromDto(Ad ad, AdUpdateDto dto) {
        ad.setCategory(dto.getCategory());
        ad.setTitle(dto.getTitle());
        ad.setDescription(dto.getDescription());
        ad.setUpdatedAt(LocalDateTime.now());
        ad.setUpdatedBy("system");
        ad.setPrice(dto.getPrice());
        ad.setCurrency(dto.getCurrency());

    }

    public AdDto toDto(Ad ad) {
        return AdDto.builder()
                .id(ad.getId())
                .title(ad.getTitle())
                .currency(ad.getCurrency())
                .price(ad.getPrice())
                .description(ad.getDescription())
                .category(ad.getCategory())
                .images(Collections.emptyList())
                .build();
    }

    public List<AdDto> toDto(List<Ad> ads) {
        return ads.stream().map(this::toDto).collect(Collectors.toList());
    }
}
