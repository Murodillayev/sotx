package uz.pdp.sotx;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateDto {
    private String bookTitle;
    private String authorId;
    private String publisher;


}
