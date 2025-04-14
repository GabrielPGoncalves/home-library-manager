package gabriel.infra.database.mapper;

import gabriel.core.domain.Book;
import gabriel.core.domain.value.Image;
import gabriel.infra.database.entity.BookEntity;
import org.apache.logging.log4j.util.Base64Util;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Base64;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookEntityMapper {

    Book toDomain(BookEntity bookEntity);

    @Mapping(target = "read", expression = "java(book.wasRead())")
    BookEntity toEntity(Book book);

    default byte[] convertImageToByteArray(Image image){
        if(image == null){
            return null;
        }

        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(image.url());
    }

    default Image convertByteArrayToImage(byte[] bytes){
        if(bytes == null){
            return null;
        }

        Base64.Encoder encoder = Base64.getEncoder();
        String url = encoder.encodeToString(bytes);

        return new Image("cover-image", url);
    }

}
