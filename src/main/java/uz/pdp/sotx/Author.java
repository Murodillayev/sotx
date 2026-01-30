package uz.pdp.sotx;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Author {
    private String id = UUID.randomUUID().toString();
    private String name;
    private LocalDateTime createdAt = LocalDateTime.now();
}
