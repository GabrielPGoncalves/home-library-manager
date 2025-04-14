package gabriel.infra.controller.web.mapper;

import gabriel.core.domain.Book;
import gabriel.core.domain.value.Image;
import gabriel.infra.controller.web.dto.BookCreateDTO;
import gabriel.infra.controller.web.dto.BookUpdateDTO;
import gabriel.infra.controller.web.dto.BookViewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookDTOMapper {

    @Mappings({
            @Mapping(target = "registerDate", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    Book toDomain(BookCreateDTO bookCreateDTO);

    @Mapping(target = "registerDate", ignore = true)
    Book toDomain(BookUpdateDTO bookUpdateDTO);

    @Mapping(target = "coverImage", source = "coverImage.url")
    BookViewDTO toViewDTO(Book book);

    @Mappings({
            @Mapping(target = "read", expression = "java(book.wasRead())"),
            @Mapping(target = "coverImage", source = "coverImage.url")
    })
    BookUpdateDTO toUpdateDTO(Book book);

    default Image convertStringUrlToImage(String coverImage){
        if(coverImage.isBlank()){
            return null;
        }

        final String BASE64_IMAGE_URL_PREFIX_REGEX = "data:image/.+;base64,";

        String url = coverImage.replaceAll(BASE64_IMAGE_URL_PREFIX_REGEX, "");
        return new Image("cover-image",  url);
    }
}
