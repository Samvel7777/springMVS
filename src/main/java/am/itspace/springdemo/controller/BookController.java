package am.itspace.springdemo.controller;

import am.itspace.springdemo.model.Book;
import am.itspace.springdemo.service.BookService;
import am.itspace.springdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final UserService userService;
    private final BookService bookService;

    @PostMapping("/saveBook")
    public String addBook(@ModelAttribute Book book) {
        String msg = book.getId() > 0 ? "Book was updated" : "Book was added";
        bookService.save(book);
        return "redirect:/?msg=" + msg;
    }

    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam("id") int id) {
        bookService.deleteById(id);
        String msg = "Book was removed";
        return "redirect:/?msg=" + msg;
    }

    @GetMapping("/editBook")
    public String editBook(@RequestParam("id") int id, ModelMap modelMap) {
        Optional<Book> book = bookService.findById(id);
        if (!book.isPresent()) {
            return "redirect:/";
        }
        modelMap.addAttribute("book", book.get());
        modelMap.addAttribute("users", userService.findAll());
        return "/editBook";
    }
}
