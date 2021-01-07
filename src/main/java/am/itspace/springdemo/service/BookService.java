package am.itspace.springdemo.service;

import am.itspace.springdemo.model.Book;
import am.itspace.springdemo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Optional<Book> findById(int id) {
        return bookRepository.findById(id);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }
}
