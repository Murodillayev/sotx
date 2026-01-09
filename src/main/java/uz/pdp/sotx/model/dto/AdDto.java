package uz.pdp.sotx.model.dto;

import uz.pdp.sotx.model.enums.Category;

import java.util.List;

public class AdDto {
    private String id;
    private String title;
    private String description;
    private Category category;
    private List<String> images;
}
