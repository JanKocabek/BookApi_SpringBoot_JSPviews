package cz.kocabek.bookapispringbootmysql.Service;


import cz.kocabek.bookapispringbootmysql.dto.BookDTO;
import cz.kocabek.bookapispringbootmysql.dto.BooksDTO;
import cz.kocabek.bookapispringbootmysql.model.Book;
import cz.kocabek.bookapispringbootmysql.repository.MemoryBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class BookService {

    private final MemoryBookRepository memoryBookRepository;

    @Autowired
    public BookService(MemoryBookRepository memoryBookRepository) {
        this.memoryBookRepository = memoryBookRepository;
    }

    public BooksDTO getBooks() {
        final String BASE_URI = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return new BooksDTO(memoryBookRepository.findBooks(), BASE_URI);
    }

    public BookDTO getBook(Long id) {
        final String BASE_URI = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        final var book = memoryBookRepository.findBookById(id);
        return new BookDTO(book, BASE_URI);

    }

    public BookDTO addBook(Book book) {
        final String BASE_URI = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        final var addedBook = memoryBookRepository.addBook(book);
        return new BookDTO(addedBook, BASE_URI);
    }

    public BookDTO updateBook(Book book) {
        final String BASE_URI = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        final var updatedBook = memoryBookRepository.updateBook(book);
        return new BookDTO(updatedBook, BASE_URI);
    }

    public void deleteBook(Long id) {
        memoryBookRepository.deleteBook(id);
    }
}

