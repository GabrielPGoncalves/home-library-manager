package gabriel.infra.controller.dto;

import java.util.UUID;

public record BookViewDTO(
        UUID id,
        String title,
        String author
) {
}
