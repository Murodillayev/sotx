package uz.pdp.sotx.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.sotx.model.enums.AuthRole;
import uz.pdp.sotx.model.enums.Category;
import uz.pdp.sotx.model.enums.Currency;

@Getter
@Setter
public class AdCreateDto {
    private String title;
    private String description;
    private Category category;
    private Double price;
    private Currency currency;
    private MultipartFile[] images;
}
