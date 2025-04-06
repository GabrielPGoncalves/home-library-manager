package gabriel.infra.controller;

import gabriel.application.service.BookService;
import gabriel.core.domain.Book;
import gabriel.core.exception.BookNotFoundException;
import gabriel.core.exception.BookValidationFailedException;
import gabriel.infra.controller.dto.BookCreateDTO;
import gabriel.infra.controller.dto.BookUpdateDTO;
import gabriel.infra.controller.dto.BookViewDTO;
import gabriel.infra.controller.mapper.BookDTOMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/library/books")
public class BookController {

    private final BookDTOMapper bookDTOMapper;
    private final BookService bookService;

    public BookController(BookDTOMapper bookDTOMapper, BookService bookService) {
        this.bookDTOMapper = bookDTOMapper;
        this.bookService = bookService;
    }

    @GetMapping
    public String books(Model model) {
        List<BookViewDTO> bookList = bookService.findAll().stream()
                .map(bookDTOMapper::toViewDTO)
                .toList();

        model.addAttribute("bookList", bookList);
        return "pages/library/books/list";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("book", new BookCreateDTO());
        model.addAttribute("editMode", false);

        return "pages/library/books/registration";
    }

    @PostMapping("/create")
    public String create(@Valid BookCreateDTO book) throws BookValidationFailedException {
        Book mappedBook = bookDTOMapper.toDomain(book);
        bookService.create(mappedBook);

        return "redirect:/library/books";
    }

    @PostMapping("/update")
    public String update(@Valid BookUpdateDTO book) throws BookNotFoundException, BookValidationFailedException {
        Book mapperBook = bookDTOMapper.toDomain(book);
        bookService.update(mapperBook);

        return "redirect:/library/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable UUID id, Model model) throws BookNotFoundException {
        Book targetBook = bookService.findById(id);
        BookUpdateDTO book = bookDTOMapper.toUpdateDTO(targetBook);

        model.addAttribute("book", book);
        model.addAttribute("editMode", true);
        return "pages/library/books/registration";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable UUID id) {
        bookService.delete(id);

        return "redirect:/library/books";
    }
}
