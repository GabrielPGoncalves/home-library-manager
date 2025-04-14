package gabriel.application.service;

import gabriel.application.gateway.BookRepository;
import gabriel.application.service.options.BookOrderOptions;
import gabriel.application.service.options.SortOptions;
import gabriel.application.validator.BookValidator;
import gabriel.application.validator.Notification;
import gabriel.core.domain.Book;
import gabriel.core.exception.BookNotFoundException;
import gabriel.core.exception.BookValidationFailedException;
import gabriel.core.exception.InvalidOperationException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class BookService {


    private final BookRepository bookRepository;
    private final BookValidator bookValidator = new BookValidator();

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findAll(BookOrderOptions order, SortOptions sort){
        if(order == null || sort == null){
            throw new IllegalArgumentException("Order and sort arguments cannot be null");
        }

        return findAll().stream()
                .sorted(createBookComparator(order, sort))
                .toList();
    }

    private Comparator<Book> createBookComparator(BookOrderOptions order, SortOptions sort){
        Comparator<Book> comparator = switch(order){
            case TITLE -> Comparator.comparing(Book::getTitle);
            case AUTHOR -> Comparator.comparing(Book::getAuthor);
            case REGISTER_DATE -> Comparator.comparing(Book::getRegisterDate);
        };

        return sort.equals(SortOptions.ASC) ? comparator : comparator.reversed();
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
            String errorMessage = "The book " + book.getId() + " has " + validationResult.getErrorsCount() + " errors: " + validationResult.getErrorsAsString();
            throw new BookValidationFailedException(errorMessage);
        }

        book.setRegisterDate(LocalDateTime.now());
        bookRepository.save(book);
    }

    public void update(Book book) throws BookNotFoundException, BookValidationFailedException {
        if(book.getId() == null) {
            throw new InvalidOperationException("Book not exists");
        }

        Notification validationResult = bookValidator.validate(book);

        if(validationResult.hasErrors()) {
            String errorMessage = "The book " + book.getId() + " has " + validationResult.getErrorsCount() + " errors: " + validationResult.getErrorsAsString();
            throw new BookValidationFailedException(errorMessage);
        }

        Book targetBook = findById(book.getId());

        if(book.equals(targetBook)) {
            return;
        }

        book.setRegisterDate(targetBook.getRegisterDate());
        bookRepository.save(book);
    }

    public void delete(UUID id) {
        bookRepository.deleteById(id);
    }

}
