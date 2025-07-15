package cz.kocabek.bookapispringbootmysql.exception;

public class DuplicatedRecordException extends RuntimeException {
    public DuplicatedRecordException(String message) {
        super(message);
    }
}
