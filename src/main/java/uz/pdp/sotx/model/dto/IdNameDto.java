package uz.pdp.sotx.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdNameDto {
    private String id;
    private String name;

    public IdNameDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
