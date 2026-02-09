package uz.pdp.sotx.model;


import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AuthUser owner;
}
