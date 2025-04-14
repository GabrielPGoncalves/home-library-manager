package gabriel.infra.gateway;

import gabriel.application.gateway.BookRepository;

import gabriel.application.service.options.BookOrderOptions;
import gabriel.application.service.options.SortOptions;
import gabriel.core.domain.Book;
import gabriel.infra.database.entity.BookEntity;
import gabriel.infra.database.mapper.BookEntityMapper;
import gabriel.infra.database.repository.BookJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Component
public class BookRepositoryImpl implements BookRepository {
    private final BookJpaRepository bookJpaRepository;
    private final BookEntityMapper bookEntityMapper;

    public BookRepositoryImpl(BookJpaRepository bookJpaRepository, BookEntityMapper bookEntityMapper) {
        this.bookJpaRepository = bookJpaRepository;
        this.bookEntityMapper = bookEntityMapper;
    }

    @Override
    public Optional<Book> findById(UUID id) {
        return bookJpaRepository.findById(id).map(bookEntityMapper::toDomain);
    }

    @Override
    public List<Book> findAll() {
        return bookJpaRepository.findAll().stream()
                .map(bookEntityMapper::toDomain)
                .toList();
    }

    private boolean isAscendingSort(SortOptions sort){
        return sort.equals(SortOptions.ASC);
    }

    @Override
    public void save(Book book) {
        BookEntity bookEntity = bookEntityMapper.toEntity(book);
        bookJpaRepository.save(bookEntity);
    }

    @Override
    public void deleteById(UUID id) {
        bookJpaRepository.deleteById(id);
    }
}
