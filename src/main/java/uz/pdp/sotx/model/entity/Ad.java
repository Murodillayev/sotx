package uz.pdp.sotx.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.sotx.model.entity.base.BaseEntity;
import uz.pdp.sotx.model.enums.Category;
import uz.pdp.sotx.model.enums.Currency;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ad extends BaseEntity {

    @Column(nullable = false)
    private String title;

    private Double price;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;
}
