package uz.pdp.sotx.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoSaveDto {

//    @NotBlank(message = "Bosh bolishi mumkin emas")
//    @Size(min = 3, max = 100, message = "Minimum 3 maxsimum 100 bolsin uzunligi")
    private String title;


    private String description;
}
