package uz.pdp.sotx.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateDto {
    private String title;
    private String userId;
    @JsonProperty("body")
    private String description;
}
