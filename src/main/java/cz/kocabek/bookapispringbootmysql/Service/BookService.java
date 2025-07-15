package cz.kocabek.bookapispringbootmysql.Service;


import cz.kocabek.bookapispringbootmysql.dto.BookDTO;
import cz.kocabek.bookapispringbootmysql.exception.BookNotFoundException;
import cz.kocabek.bookapispringbootmysql.model.Book;
import cz.kocabek.bookapispringbootmysql.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }


    public Book getBook(Long id) {
        final var bookResult = bookRepository.findById(id);
        return (bookResult.orElseThrow(() -> new BookNotFoundException("Book with id: %d not found".formatted(id))));
    }

    @Transactional
    public BookDTO addBook(Book book) {
        final String BASE_URI = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        final var addedBook = bookRepository.save(book);
        return new BookDTO(addedBook, BASE_URI);
    }

    @Transactional
    public BookDTO updateBook(Book book) {
        final String BASE_URI = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        final var updatedBook = bookRepository.save(book);
        return new BookDTO(updatedBook, BASE_URI);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id))
            throw new BookNotFoundException("Book with ID %d not found and can't be deleted".formatted(id));
        bookRepository.deleteById(id);
    }
}

