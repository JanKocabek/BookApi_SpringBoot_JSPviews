package cz.kocabek.bookapispringbootmysql.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import cz.kocabek.bookapispringbootmysql.model.Book;
import lombok.Data;
import org.springframework.http.HttpStatus;

@JsonPropertyOrder({"status", "book", "self"})
@Data
public class BookDTO {
    @JsonView( View.BookWithStatus.class)
    private int status;
    @JsonView(View.Book.class)
    private final Book book;
    @JsonView(View.Book.class)
    @JsonProperty("self")
    private final String selfUri;

    @JsonCreator
    public BookDTO(Book book, String uri) {
        this.book = book;
        this.selfUri = uri + "/books/" + book.getId();
    }

    public void setStatus(HttpStatus status) {
        this.status = status.value();
    }

    public BookDTO withStatus(HttpStatus status) {
        this.status = status.value();
        return this;
    }

}
