package cz.kocabek.bookapispringbootmysql.model;

import com.fasterxml.jackson.annotation.JsonView;
import cz.kocabek.bookapispringbootmysql.api.dto.View;
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
@JsonView(View.Book.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "Book must have ISBN")
    @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$", message = "ISBN must be 10  or 13 digits long and can contain only digits and dashes")
    private String isbn;


    private String title;


    private String author;


    private String publisher;

    private String type;


}
