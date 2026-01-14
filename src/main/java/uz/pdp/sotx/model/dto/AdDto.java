package uz.pdp.sotx.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.sotx.model.enums.Category;
import uz.pdp.sotx.model.enums.Currency;

import java.util.List;

@Builder
@Getter
@Setter
public class AdDto {
    private String id;
    private String title;
    private Double price;
    private Currency currency;
    private String description;
    private Category category;
    private List<String> images;
}
