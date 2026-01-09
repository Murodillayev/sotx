package uz.pdp.sotx.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.sotx.model.dto.AdCreateDto;
import uz.pdp.sotx.model.dto.AdUpdateDto;
import uz.pdp.sotx.model.entity.Ad;

@Component
public class AdMapper {

    public Ad fromDto(AdCreateDto dto) {
        Ad ad = new Ad();
        ad.setCategory(dto.getCategory());
        ad.setTitle(dto.getTitle());
        ad.setDescription(dto.getDescription());

        // imgae service chaqirilib sert qilinadi
        return ad;
    }

    public void fromDto(Ad ad,AdUpdateDto dto) {
//        ad.setCategory(dto.getCategory());
//        ad.setTitle(dto.getTitle());
//        ad.setDescription(dto.getDescription());

    }
}
