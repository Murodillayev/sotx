package uz.pdp.sotx.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.sotx.model.enums.Category;

@Getter
@Setter
public class AdCreateDto {
    private String title;
    private String description;
    private Category category;
    private MultipartFile[] images;
}
