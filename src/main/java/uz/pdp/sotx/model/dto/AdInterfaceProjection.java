package uz.pdp.sotx.model.dto;

import uz.pdp.sotx.model.enums.Category;
import uz.pdp.sotx.model.enums.Currency;

public interface AdInterfaceProjection {
    String getId();

    String getTitle();

    Currency getCurrency();

    Category getCategory();

    Double getPrice();

    String getDescription();
}
