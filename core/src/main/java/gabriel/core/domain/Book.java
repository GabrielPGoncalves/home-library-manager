package gabriel.core.domain;

import gabriel.core.domain.value.Image;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Book {

    public enum Type {
        LITERARY, EXHIBITION
    }

    private UUID id;
    private String title;
    private String author;
    private Image coverImage;
    private Type type;
    private String isbn;
    private LocalDate acquisitionDate;
    private LocalDate readingStartDate;
    private boolean read;
    private LocalDate readingEndDate;
    private LocalDateTime registerDate;

    public Book(UUID id, String title, String author, Image coverImage, Type type, String isbn, LocalDate acquisitionDate, LocalDate readingStartDate, boolean read, LocalDate readingEndDate, LocalDateTime registerDate) {
        this(id, title, author, coverImage, type, isbn);
        this.acquisitionDate = acquisitionDate;
        this.readingStartDate = readingStartDate;
        this.read = read;
        this.readingEndDate = readingEndDate;
        this.registerDate = registerDate;
    }

    public Book(UUID id, String title, String author, Image coverImage, Type type, String isbn) {
        this(id, title, author, type);
        this.coverImage = coverImage;
        this.isbn = isbn;
    }

    public Book(UUID id, String title, String author, Type type) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.type = type;
    }

    public Book(){
    }

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

    public Image getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Image coverImage) {
        this.coverImage = coverImage;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isRead() {
        return read;
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

    public boolean wasRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public LocalDate getReadingEndDate() {
        return readingEndDate;
    }

    public void setReadingEndDate(LocalDate readingEndDate) {
        this.readingEndDate = readingEndDate;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;

        return read == book.read && Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(coverImage, book.coverImage) && type == book.type && Objects.equals(isbn, book.isbn) && Objects.equals(acquisitionDate, book.acquisitionDate) && Objects.equals(readingStartDate, book.readingStartDate) && Objects.equals(readingEndDate, book.readingEndDate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Objects.hashCode(author);
        result = 31 * result + Objects.hashCode(coverImage);
        result = 31 * result + Objects.hashCode(type);
        result = 31 * result + Objects.hashCode(isbn);
        result = 31 * result + Objects.hashCode(acquisitionDate);
        result = 31 * result + Objects.hashCode(readingStartDate);
        result = 31 * result + Boolean.hashCode(read);
        result = 31 * result + Objects.hashCode(readingEndDate);
        return result;
    }
}
