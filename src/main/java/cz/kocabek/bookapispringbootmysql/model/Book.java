package cz.kocabek.bookapispringbootmysql.model;

import com.fasterxml.jackson.annotation.JsonView;
import cz.kocabek.bookapispringbootmysql.dto.View;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @JsonView(View.Book.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(View.Book.class)
    @NotNull(message = "Book must have ISBN")
    @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$", message = "ISBN must be 10  or 13 digits long and can contain only digits and dashes")
    private String isbn;

    @JsonView(View.Book.class)
    private String title;

    @JsonView(View.Book.class)
    private String author;

    @JsonView(View.Book.class)
    private String publisher;

    @JsonView(View.Book.class)
    private String type;


}
