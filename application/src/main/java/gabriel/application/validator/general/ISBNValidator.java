package gabriel.application.validator.general;

public class ISBNValidator {

    public boolean validate(String isbn){
        if(isbn == null){
            return false;
        }

        isbn = isbn.replaceAll("-", "");

        int length = isbn.length();

        if(length == 10){
            return validateISBN10(isbn);
        }

        if (length == 13){
            return validateISBN13(isbn);
        }

        return false;
    }

    private boolean validateISBN10(String isbn10){
        try{
            final int ISBN_LENGTH = 10;
            final int ISBN_VALIDATION_DIGIT_INDEX = ISBN_LENGTH - 1;

            int sum = 0;
            String[] isbnNumbers = isbn10.split("");

            for(int i = 0; i < ISBN_LENGTH - 1; i++){
                int digit = Integer.parseInt(isbnNumbers[i]);
                int weight = i + 1;
                sum += (digit * weight);
            }

            String finalChar = isbnNumbers[ISBN_VALIDATION_DIGIT_INDEX];
            int validationDigit = finalChar.equalsIgnoreCase("X") ? 10 : Integer.parseInt(finalChar);
            sum += (validationDigit * ISBN_LENGTH);

            return sum % 11 == 0;
        } catch(NumberFormatException exc){
            return false;
        }
    }

    private boolean validateISBN13(String isbn13){
        try{
            final int ISBN_LENGTH = 13;
            final int ISBN_VALIDATION_DIGIT_INDEX = ISBN_LENGTH - 1;

            int sum = 0;
            String[] isbnNumbers = isbn13.split("");

            for(int i = 0; i < ISBN_LENGTH - 1; i++){
                int digit = Integer.parseInt(isbnNumbers[i]);
                int weight = i % 2 == 0 ? 1 : 3;
                sum += (digit * weight);
            }

            int validationDigit = Integer.parseInt(isbnNumbers[ISBN_VALIDATION_DIGIT_INDEX]);

            return validationDigit == (10 - (sum % 10)) % 10;
        } catch(NumberFormatException exc){
            return false;
        }
    }
}
