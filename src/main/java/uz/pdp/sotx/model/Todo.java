package uz.pdp.sotx.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.context.annotation.Lazy;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}
