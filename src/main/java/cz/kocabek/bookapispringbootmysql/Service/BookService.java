package cz.kocabek.bookapispringbootmysql.Service;


import cz.kocabek.bookapispringbootmysql.dto.BookDTO;
import cz.kocabek.bookapispringbootmysql.dto.BooksDTO;
import cz.kocabek.bookapispringbootmysql.exception.BookNotFoundException;
import cz.kocabek.bookapispringbootmysql.model.Book;
import cz.kocabek.bookapispringbootmysql.repository.BookRepository;
import cz.kocabek.bookapispringbootmysql.repository.MemoryBookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class BookService {

    private final MemoryBookRepository memoryBookRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository, MemoryBookRepository memoryBookRepository) {
        this.bookRepository = bookRepository;
        this.memoryBookRepository = memoryBookRepository;
    }

    public BooksDTO getBooks() {
        final String BASE_URI = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return new BooksDTO(bookRepository.findAll(), BASE_URI);
    }

    public BookDTO getBook(Long id) {
        final String BASE_URI = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        final var bookResult = bookRepository.findById(id);
        return new BookDTO(bookResult.orElseThrow(() -> new BookNotFoundException("Book with id: %d not found".formatted(id))), BASE_URI);
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
       bookRepository.deleteById(id);
    }
}

