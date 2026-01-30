package uz.pdp.sotx;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookDto {
    private String id;
    private String title;
    private AuthorDto author;
    private String publisher;
    private String createdAt;

}
