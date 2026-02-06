package uz.pdp.sotx.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDto {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}
