package uz.pdp.sotx;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class BookService {

    Logger logger = Logger.getLogger(BookService.class.getName());
    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);

    public BookDto create(BookCreateDto dto) {
        Author author = new Author();// dto.getAuthorId()
        author.setName("Olim");

        Book book = mapper.fromDto(dto);
        book.setAuthor(author);

        logger.info("Creating book " + book);
        return mapper.toDto(book);
    }


}
