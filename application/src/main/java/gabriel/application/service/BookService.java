package gabriel.application.service;

import gabriel.application.gateway.BookRepository;
import gabriel.application.validator.BookValidator;
import gabriel.application.validator.Notification;
import gabriel.core.domain.Book;
import gabriel.core.exception.BookNotFoundException;
import gabriel.core.exception.BookValidationFailedException;
import gabriel.core.exception.InvalidOperationException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookService {

    private final BookRepository bookRepository;
    private final BookValidator bookValidator = new BookValidator();

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(UUID id) throws BookNotFoundException {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found"));
    }

    public void create(Book book) throws BookValidationFailedException {
        if(book.getId() != null) {
            throw new InvalidOperationException("Book with id " + book.getId() + " already exists");
        }

        Notification validationResult = bookValidator.validate(book);

        if(validationResult.hasErrors()) {
            throw new BookValidationFailedException(validationResult.getErrorsAsString());
        }

        bookRepository.save(book);
    }

    public void update(Book book) throws BookNotFoundException, BookValidationFailedException {
        if(book.getId() == null) {
            throw new InvalidOperationException("Book not exists");
        }

        Notification validationResult = bookValidator.validate(book);

        if(validationResult.hasErrors()) {
            throw new BookValidationFailedException(validationResult.getErrorsAsString());
        }

        Book targetBook = findById(book.getId());

        if(book.equals(targetBook)) {
            return;
        }

        bookRepository.save(book);
    }

    public void delete(UUID id) {
        bookRepository.deleteById(id);
    }
}
