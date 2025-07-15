package cz.kocabek.bookapispringbootmysql.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookNotFoundException.class)
    public ErrorResponse handleBookNotFoundException(BookNotFoundException ex) {
        final var problemDetail = buildProblemDetail(HttpStatus.NOT_FOUND, ex.getMessage(), "Book not found", "book-not-found");
        return ErrorResponse.builder(ex, problemDetail).build();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicatedRecordException.class)
    public ErrorResponse handleDuplicatedRecordException(DuplicatedRecordException ex) {
        final var problemDetail = buildProblemDetail(HttpStatus.CONFLICT, ex.getMessage(), "Book Duplication", "book-duplication");
        return ErrorResponse.builder(ex, problemDetail).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        final var fieldErrors = ex.getBindingResult().getFieldErrors();
        StringBuilder errorDetails = new StringBuilder();
        for (var fieldError : fieldErrors) {
            errorDetails.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append("\n");
        }
        final var problemDetail = buildProblemDetail(HttpStatus.BAD_REQUEST, errorDetails.toString(), "Invalid input data", "invalid-input-data");
        return ErrorResponse.builder(ex, problemDetail).build();
    }


    private static ProblemDetail buildProblemDetail(HttpStatus status, String message, String title, String type) {
        final var problemDetail = ProblemDetail.forStatusAndDetail(status, message);
        final var errPath = "/books/error";
        final var uri = ServletUriComponentsBuilder.fromCurrentContextPath().path(errPath).toUriString();
        problemDetail.setTitle(title);
        problemDetail.setType(URI.create(uri + "/" + type));
        problemDetail.setProperty("timestamp", Instant.now().toString());
        return problemDetail;
    }

}
