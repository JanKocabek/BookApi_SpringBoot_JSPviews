package cz.kocabek.bookapispringbootmysql.controller;


import cz.kocabek.bookapispringbootmysql.Service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class ViewController {
    private final BookService bookService;

    public ViewController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public String getBooks(Model model) {
        final var books = bookService.getBooks();
        model.addAttribute("books", books);
        return "books";
    }
    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") Long id, Model model) {
        final var book = bookService.getBook(id);
        model.addAttribute("book", book);
        return "book";
    }
}
