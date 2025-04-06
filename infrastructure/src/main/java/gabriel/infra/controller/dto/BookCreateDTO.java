package gabriel.infra.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class BookCreateDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String title;

    @NotBlank
    @Size(min = 2, max = 100)
    private String author;

    private String coverImage;

    private LocalDate acquisitionDate;

    @NotBlank
    private String type;

    @Size(min = 10, max = 13)
    private String isbn;

    private LocalDate readingStartDate;

    private Boolean read;

    private LocalDate readingEndDate;

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

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
