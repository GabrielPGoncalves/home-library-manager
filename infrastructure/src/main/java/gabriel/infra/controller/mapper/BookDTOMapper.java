package gabriel.infra.controller.mapper;

import gabriel.core.domain.Book;
import gabriel.core.domain.value.Image;
import gabriel.infra.controller.dto.BookCreateDTO;
import gabriel.infra.controller.dto.BookUpdateDTO;
import gabriel.infra.controller.dto.BookViewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookDTOMapper {


    @Mapping(target = "id", ignore = true)
    Book toDomain(BookCreateDTO bookCreateDTO);

    Book toDomain(BookUpdateDTO bookUpdateDTO);

    BookViewDTO toViewDTO(Book book);

    @Mappings({
            @Mapping(target = "read", expression = "java(book.wasRead())"),
            @Mapping(target = "coverImage", source = "coverImage.url")
    })
    BookUpdateDTO toUpdateDTO(Book book);

    default Image convertStringUrlToImage(String coverImage){
        final String BASE64_IMAGE_URL_PREFIX_REGEX = "data:image/.+;base64,";

        String url = coverImage.replaceAll(BASE64_IMAGE_URL_PREFIX_REGEX, "");
        return new Image("cover-image",  url);
    }
}
