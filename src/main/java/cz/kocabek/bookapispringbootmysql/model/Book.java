package cz.kocabek.bookapispringbootmysql.model;

import com.fasterxml.jackson.annotation.JsonView;
import cz.kocabek.bookapispringbootmysql.api.dto.View;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotEmpty(message = "Book must have ISBN")
    @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$", message = "ISBN can contain only  10 or 13  digits, and dashes")
    @Size(min = 10, max = 20, message = "ISBN must be between 10 to 20 characters")
    @Column(unique = true, length = 20, nullable = false)
    private String isbn;

    @NotEmpty(message = "Book must have title")
    private String title;

    @NotEmpty(message = "Book must have author")
    @Column(nullable = false)
    private String author;

    @NotEmpty(message = "Book must have publisher")
    @Column(nullable = false)
    private String publisher;

    @NotEmpty(message = "Book must have type")
    @Column(nullable = false)
    private String type;


}
