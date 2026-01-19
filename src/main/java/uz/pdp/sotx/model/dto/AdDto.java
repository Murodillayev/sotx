package uz.pdp.sotx.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.sotx.model.enums.Category;
import uz.pdp.sotx.model.enums.Currency;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class AdDto {
    private String id;
    private String title;
    private Double price;
    private Currency currency;
    private String description;
    private Category category;
    private List<String> images;

    public AdDto(String id, String title, Double price, Currency currency, String description, Category category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.currency = currency;
        this.description = description;
        this.category = category;
        images = new ArrayList<>();
    }
}
