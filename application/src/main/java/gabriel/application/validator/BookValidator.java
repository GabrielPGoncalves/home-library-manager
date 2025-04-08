package gabriel.application.validator;

import gabriel.core.domain.Book;

import java.time.LocalDate;

public class BookValidator {

    public Notification validate(Book book) {
        final Notification notification = new Notification();

        if(!checkIfTitleIsValid(book.getTitle())){
            notification.addError("The book doesn't have a valid title");
        }

        if(!checkIfAuthorIsValid(book.getAuthor())){
            notification.addError("The book doesn't have a valid author");
        }

        if(!checkIfTypeIsValid(book.getType())){
            notification.addError("The book doesn't have a valid type");
        }

        if(!checkIfAcquisitionDateIsValid(book.getAcquisitionDate())){
            notification.addError("The book doesn't have a valid acquisition date");
        }

        if(!checkIfReadingStartDateIsValid(book.getReadingStartDate())){
            notification.addError("The book doesn't have a valid reading start date");
        }

        if(!checkIfReadingEndDateIfAfterStartDate(book.getReadingStartDate(), book.getReadingEndDate())){
            notification.addError("The book has a reading end date earlier than the reading start date");
        }

        if(!checkIfBookHasReadingDateOnlyIfReadIsTrue(book)){
            notification.addError("The book has the reading date, but has not been read");
        }


        return notification;
    }

    private boolean checkIfTitleIsValid(String title) {
        if(title == null || title.isBlank()) {
            return false;
        }

        int length = title.length();

        return length >= 2 && length <= 100;
    }

    private boolean checkIfAuthorIsValid(String author) {
        if(author == null || author.isBlank()) {
            return false;
        }

        int length = author.length();

        return length >= 2 && length <= 100;
    }

    private boolean checkIfTypeIsValid(Book.Type type) {
        return type != null;
    }

    private boolean checkIfAcquisitionDateIsValid(LocalDate acquisitionDate) {
        if(acquisitionDate == null) {
            return true;
        }

        return !acquisitionDate.isAfter(LocalDate.now());
    }

    private boolean checkIfReadingStartDateIsValid(LocalDate readingStartDate) {
        if(readingStartDate == null) {
            return true;
        }

        return !readingStartDate.isAfter(LocalDate.now());
    }

    private boolean checkIfReadingEndDateIfAfterStartDate(LocalDate readingStartDate, LocalDate readingEndDate){
        if(readingStartDate == null || readingEndDate == null){
            return true;
        }

        return readingEndDate.isAfter(readingStartDate);
    }

    private boolean checkIfBookHasReadingDateOnlyIfReadIsTrue(Book book) {
        if(book.getReadingEndDate() == null) {
            return true;
        }

        return book.wasRead();
    }

}
