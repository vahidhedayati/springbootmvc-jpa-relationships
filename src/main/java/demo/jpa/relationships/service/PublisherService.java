package demo.jpa.relationships.service;

import demo.jpa.relationships.dto.AddAuthorToBook;
import demo.jpa.relationships.dto.AddPublisherDto;
import demo.jpa.relationships.dto.AddPublisherToBook;
import demo.jpa.relationships.entity.Author;
import demo.jpa.relationships.entity.Book;
import demo.jpa.relationships.entity.Publisher;
import demo.jpa.relationships.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherRepository repository;


    public PublisherService(@Qualifier("currentPublisherRepository") PublisherRepository repository) {
        this.repository = repository;
    }

    @Autowired
    BookService bookService;

    public Optional<Publisher> findById(Long id){
        return repository.findById(id);
    }

    public Publisher save(Publisher entity) {
        return repository.save(entity);
    }

    public Publisher save(AddPublisherDto entity) {
        Publisher item = new Publisher();
        item.setName(entity.getName());
        return repository.save(item);
    }

    public List<Publisher> findAll() {
        return (List<Publisher>) repository.findAll();
    }

    public Map<String ,Object> assignPublisherToBook(AddPublisherToBook publisherToBook, BindingResult bindingResult) {
        Map<String ,Object> result = new HashMap<>();
        Optional<Publisher> publisherFound = findById(publisherToBook.getPublisherId());
        if (publisherFound.isPresent()) {
            Publisher publisher = publisherFound.get();
            Optional<Book> bookFound = bookService.findById(publisherToBook.getBookId());
            if (bookFound.isPresent()) {
                publisher = addPublisherToBook(publisher, bookFound.get());
                addPublisherToBook(publisher, bookFound.get());
            } else {
                bindingResult.addError(new FieldError("publisherToBook", "bookId", "book id was not found"));
                result.put("error", bindingResult);
            }
            result.put("publisher", publisher);
        } else {
            bindingResult.addError(new FieldError("publisherToBook", "publisherId", "author id was not found"));
            result.put("error", bindingResult);
        }
        return result;
    }

    private Publisher addPublisherToBook(Publisher publisher, Book book) {
        book.setPublisher(publisher);
        publisher.addNewBook(book);
        bookService.save(book);
        return  repository.save(publisher);
    }
}
