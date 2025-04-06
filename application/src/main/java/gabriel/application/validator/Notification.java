package gabriel.application.validator;

import java.util.ArrayList;
import java.util.List;

public class Notification {
    private final List<String> errors = new ArrayList<>();

    public void addError(String message){
        errors.add(message);
    }

    public boolean hasErrors(){
        return !errors.isEmpty();
    }

    public List<String> getErrors(){
        return errors;
    }

    public String getErrorsAsString(){
        return String.join("; ", errors);
    }
}
