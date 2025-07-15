package cz.kocabek.bookapispringbootmysql.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import cz.kocabek.bookapispringbootmysql.model.Book;
import org.springframework.http.HttpStatus;

import java.util.List;

public class BooksDTO {
    @JsonView(View.Book.class)
    @JsonProperty("books")
    private final List<BookDTO> bookDTOS;
    @JsonView(View.Book.class)
    private int status;
    @JsonView(View.Book.class)
    private final int count;

    public BooksDTO(List<Book> books, String uri) {
        this.bookDTOS = wrapBooksToDTO(books, uri);
        this.count = books.size();
    }


    private List<BookDTO> wrapBooksToDTO(List<Book> books, String uri) {
        return books.stream().map(book -> new BookDTO(book, uri)).toList();
    }

    public List<BookDTO> getBookDTOS() {
        return bookDTOS;
    }

    public int getStatus() {
        return status;
    }

    public int getCount() {
        return count;
    }

    public void setStatus(HttpStatus status) {
        this.status = status.value();
    }

    public BooksDTO withStatus(HttpStatus status) {
        this.status = status.value();
        return this;
    }


    @Override
    public String toString() {
        return "BooksDTO{" +
               "status=" + status +
               ", count=" + count +
               ", bookDTOS=" + bookDTOS +
               '}';
    }
}
