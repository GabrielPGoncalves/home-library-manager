package gabriel.infra.controller.api;

import gabriel.application.service.BookService;
import gabriel.application.service.options.BookOrderOptions;
import gabriel.application.service.options.SortOptions;
import gabriel.core.domain.Book;
import gabriel.infra.controller.api.pagination.InvalidPageNumberException;
import gabriel.infra.controller.api.pagination.Page;
import gabriel.infra.controller.api.pagination.PaginationService;
import gabriel.infra.controller.web.dto.BookViewDTO;
import gabriel.infra.controller.web.mapper.BookDTOMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/library/books")
public class BookApiController {

    private final BookService bookService;
    private final BookDTOMapper bookDTOMapper;
    private final PaginationService bookPaginationService;

    public BookApiController(BookService bookService, BookDTOMapper bookDTOMapper, PaginationService bookPaginationService) {
        this.bookService = bookService;
        this.bookDTOMapper = bookDTOMapper;
        this.bookPaginationService = bookPaginationService;
    }

    @GetMapping
    public ResponseEntity<Page<BookViewDTO>> list(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String order, @RequestParam(required = false) String sort) throws InvalidPageNumberException {
        Supplier<List<Book>> resourceSupplier;

        if(order == null || order.isBlank()){
            resourceSupplier = bookService::findAll;
        } else {
            BookOrderOptions orderOption = BookOrderOptions.valueOf(order.toUpperCase());
            SortOptions sortOption = (sort == null || sort.isBlank()) ? SortOptions.ASC : SortOptions.valueOf(sort.toUpperCase());

            resourceSupplier = () -> bookService.findAll(orderOption, sortOption);
        }

        Page<BookViewDTO> resultPage = bookPaginationService.generatePage(resourceSupplier, bookDTOMapper::toViewDTO, page, size);

        return ResponseEntity.ok(resultPage);
    }
}