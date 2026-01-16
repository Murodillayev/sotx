package uz.pdp.sotx.criteria;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.sotx.model.enums.Category;

@Getter
@Setter
@Builder
public class AdCriteria {
    private String search;
    private Category category;
    private Integer page;
    private Integer size;
}

