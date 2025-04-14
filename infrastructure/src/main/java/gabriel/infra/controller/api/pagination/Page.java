package gabriel.infra.controller.api.pagination;

import java.util.List;

public record Page<T>(int number, int size, int totalPageCount, List<T> content) {

}
