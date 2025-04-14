package gabriel.infra.controller.web.dto;

import java.util.UUID;

public record BookViewDTO(
        UUID id,
        String title,
        String author,
        String coverImage
) {
}
