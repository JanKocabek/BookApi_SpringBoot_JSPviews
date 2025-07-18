package cz.kocabek.bookapispringbootmysql.api.controller;


import com.fasterxml.jackson.annotation.JsonView;
import cz.kocabek.bookapispringbootmysql.Service.BookService;
import cz.kocabek.bookapispringbootmysql.api.dto.BookDTO;
import cz.kocabek.bookapispringbootmysql.api.dto.BooksDTO;
import cz.kocabek.bookapispringbootmysql.api.dto.View;
import cz.kocabek.bookapispringbootmysql.api.service.RestService;
import cz.kocabek.bookapispringbootmysql.model.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/books")
public class RestController {

    private final BookService bookService;
    private final RestService restService;

    private static String getBaseUri() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }

    @Autowired
    public RestController(BookService bookService, RestService restService) {
        this.bookService = bookService;
        this.restService = restService;
    }

    @JsonView(View.Book.class)
    @GetMapping("")
    public ResponseEntity<BooksDTO> getBooks() {

        final var books = bookService.getBooks();
        final var dto = new BooksDTO(books, getBaseUri());
        return ResponseEntity.ok(dto.withStatus(HttpStatus.OK));
    }

    @JsonView(View.BookWithStatus.class)
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(wrapBookToDTO(bookService.getBook(id)).withStatus(HttpStatus.OK));
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody Book book) {
        final var addedBook = bookService.addBook(book);
        final var bookDTO = wrapBookToDTO(addedBook);
        return ResponseEntity.created(ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(addedBook.getId()).toUri())
                .body(bookDTO.withStatus(HttpStatus.CREATED));
    }

    @PutMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookDTO> updateBook(@Valid @RequestBody Book book) {
        final var updatedBook = bookService.updateBook(book);
        final var bookDTO = wrapBookToDTO(updatedBook);
        return ResponseEntity.ok(bookDTO.withStatus(HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        restService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    private static BookDTO wrapBookToDTO(Book book) {
        return new BookDTO(book, getBaseUri());
    }
}
