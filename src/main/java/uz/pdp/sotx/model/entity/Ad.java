package uz.pdp.sotx.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.sotx.model.base.BaseEntity;
import uz.pdp.sotx.model.enums.Category;
import uz.pdp.sotx.model.enums.Currency;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ad extends BaseEntity {
    private String title;
    private Double price;
    private Currency currency;
    private String description;
    private Category category;
}
