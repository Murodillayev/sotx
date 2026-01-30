package uz.pdp.sotx;

import org.mapstruct.Mapper;

@Mapper
public interface AuthorMapper {

    AuthorDto toDto(Author author);
}
