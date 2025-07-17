package cz.kocabek.bookapispringbootmysql.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import cz.kocabek.bookapispringbootmysql.model.Book;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;
@JsonPropertyOrder({"status", "count", "books"})
@Data
public class BooksDTO {
    @JsonView(View.Book.class)
    @JsonProperty("books")
    private final List<BookDTO> bookDTOList;
    @JsonView(View.Book.class)
    private int status;
    @JsonView(View.Book.class)
    private final int count;

    public BooksDTO(List<Book> books, String uri) {
        this.bookDTOList = wrapBooksToDTO(books, uri);
        this.count = books.size();
    }


    private List<BookDTO> wrapBooksToDTO(List<Book> books, String uri) {
        return books.stream().map(book -> new BookDTO(book, uri)).toList();
    }

    public void setStatus(HttpStatus status) {
        this.status = status.value();
    }

    public BooksDTO withStatus(HttpStatus status) {
        this.status = status.value();
        return this;
    }

}
