package cz.kocabek.bookapispringbootmysql.controller;


import com.fasterxml.jackson.annotation.JsonView;
import cz.kocabek.bookapispringbootmysql.Service.BookService;
import cz.kocabek.bookapispringbootmysql.dto.BookDTO;
import cz.kocabek.bookapispringbootmysql.dto.BooksDTO;
import cz.kocabek.bookapispringbootmysql.dto.View;
import cz.kocabek.bookapispringbootmysql.model.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    private final BookService bookService;

    private static String getBaseUri() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }

    @Autowired
    public RestController(BookService bookService) {
        this.bookService = bookService;
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
        final var book = bookService.getBook(id);
        final var dto= new BookDTO(book,getBaseUri());
        return ResponseEntity.ok(dto.withStatus(HttpStatus.OK));
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody Book book) {
        final BookDTO addedBook = bookService.addBook(book).withStatus(HttpStatus.CREATED);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedBook.getBook().getId()).toUri()).body(addedBook);
    }

    @PutMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookDTO> updateBook(@Valid @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(book).withStatus(HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
