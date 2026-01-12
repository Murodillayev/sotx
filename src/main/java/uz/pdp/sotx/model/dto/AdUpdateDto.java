package uz.pdp.sotx.model.dto;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.sotx.model.enums.Category;
import uz.pdp.sotx.model.enums.Currency;

@Getter
@Setter
public class AdUpdateDto {
    private String title;
    private String description;
    private Category category;
    private Double price;
    private Currency currency;
}
