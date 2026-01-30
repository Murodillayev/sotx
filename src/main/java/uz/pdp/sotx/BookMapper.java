package uz.pdp.sotx;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        uses = {AuthorMapper.class}
)
public interface BookMapper {

    @Mapping(target = "title", source = "bookTitle")
    Book fromDto(BookCreateDto dto);

    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "dd-MM-yyyy HH:mm")
//    @Mapping(target = "author.id", source = "author.id")
//    @Mapping(target = "author.name", source = "author.name")
    BookDto toDto(Book book);

}
