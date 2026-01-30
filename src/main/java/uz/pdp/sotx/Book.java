package uz.pdp.sotx;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
public class Book {

    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String title;
    private String publisher;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    private Author author;
}
