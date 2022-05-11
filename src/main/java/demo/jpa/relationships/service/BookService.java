package demo.jpa.relationships.service;

import demo.jpa.relationships.dto.AddBookDto;
import demo.jpa.relationships.entity.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final CrudRepository repository;

    public BookService(@Qualifier("currentBookRepository") CrudRepository repository) {
        this.repository = repository;
    }

    public Optional<Book> findById(Long id){
        return repository.findById(id);
    }

    public Book save(Book entity) {
        return (Book) repository.save(entity);
    }

    public Book save(AddBookDto entity) {
        Book book = new Book();
        book.setName(entity.getName());
        return (Book) repository.save(book);
    }

    public List<Book> findAll() {
        return (List<Book>) repository.findAll();
    }

}
