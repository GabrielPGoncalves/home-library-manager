package gabriel.infra.database.entity;

import gabriel.core.domain.Book;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_book")
public class BookEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 100)
    private String author;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] coverImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Book.Type type;

    @Column(length = 13)
    private String isbn;

    private LocalDate acquisitionDate;

    private LocalDate readingStartDate;

    @Column(nullable = false)
    private Boolean read;

    private LocalDate readingEndDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public byte[] getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(byte[] coverImage) {
        this.coverImage = coverImage;
    }

    public Book.Type getType() {
        return type;
    }

    public void setType(Book.Type type) {
        this.type = type;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public LocalDate getReadingStartDate() {
        return readingStartDate;
    }

    public void setReadingStartDate(LocalDate readingStartDate) {
        this.readingStartDate = readingStartDate;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public LocalDate getReadingEndDate() {
        return readingEndDate;
    }

    public void setReadingEndDate(LocalDate readingEndDate) {
        this.readingEndDate = readingEndDate;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof BookEntity that)) return false;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
