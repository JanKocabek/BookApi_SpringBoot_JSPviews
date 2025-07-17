package cz.kocabek.bookapispringbootmysql.api.service;

import cz.kocabek.bookapispringbootmysql.Service.BookService;
import cz.kocabek.bookapispringbootmysql.exception.BookNotFoundException;
import cz.kocabek.bookapispringbootmysql.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class RestService {

    private final BookService bookService;
    private final BookRepository bookRepository;

    public RestService(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    public void deleteBookById(Long id) {
        if (!bookRepository.existsById(id))
            throw new BookNotFoundException("Book with ID %d not found and can't be deleted".formatted(id));
        bookRepository.deleteById(id);
    }

}
