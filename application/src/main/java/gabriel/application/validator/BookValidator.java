package gabriel.application.validator;

import gabriel.core.domain.Book;

public class BookValidator {


    public Notification validate(Book book) {
        final Notification notification = new Notification();

        if(!checkIfBookHasReadingDateOnlyIfReadIsTrue(book)){
            notification.addError("The book with id " + book.getId() + " has the reading date, but has not been read");
        }


        return notification;
    }

    private boolean checkIfBookHasReadingDateOnlyIfReadIsTrue(Book book) {
        if(book.getReadingEndDate() == null) {
            return true;
        }

        return book.wasRead();
    }

}
