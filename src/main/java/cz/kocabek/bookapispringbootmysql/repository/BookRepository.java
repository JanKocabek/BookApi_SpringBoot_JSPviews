package cz.kocabek.bookapispringbootmysql.repository;

import cz.kocabek.bookapispringbootmysql.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
}
