package uz.pdp.sotx.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorDto {
    private String message;
    private Integer code;
    private String path;
    private String timestamp;
}
