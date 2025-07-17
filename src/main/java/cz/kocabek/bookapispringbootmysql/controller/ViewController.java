package cz.kocabek.bookapispringbootmysql.controller;


import cz.kocabek.bookapispringbootmysql.Service.BookService;
import cz.kocabek.bookapispringbootmysql.model.Book;
import jakarta.persistence.EntityResult;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/form")
    public String getForm(@ModelAttribute("book") Book book, Model model) {
        model.addAttribute("action", "add");
        return "form";
    }

    @PostMapping("/add")
    public String addBook(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            model.addAttribute("action", "add");
            return "form";
        }
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String getUpdateForm(@PathVariable("id") Long id,Model model) {
        final var book = bookService.getBook(id);
        model.addAttribute("book", book);
        model.addAttribute("action", "update");
        return "form";
    }

    @PostMapping("/update")
    public String updateBook(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            model.addAttribute("action", "update");
            return "form";
        }
        bookService.updateBook(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }


}
