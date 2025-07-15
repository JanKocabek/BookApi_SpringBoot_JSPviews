package cz.kocabek.bookapispringbootmysql.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import cz.kocabek.bookapispringbootmysql.model.Book;
import org.springframework.http.HttpStatus;


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

    public Book getBook() {
        return book;
    }

    public String getSelfUri() {
        return selfUri;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status.value();
    }

    public BookDTO withStatus(HttpStatus status) {
        this.status = status.value();
        return this;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
               "book=" + book +
               ", selfUri='" + selfUri + '\'' +
               '}';
    }
}
