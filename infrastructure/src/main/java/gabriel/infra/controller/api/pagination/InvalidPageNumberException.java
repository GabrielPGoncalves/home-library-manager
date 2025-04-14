package gabriel.infra.controller.api.pagination;

public class InvalidPageNumberException extends Exception {
    public InvalidPageNumberException(String message) {
        super(message);
    }
}
