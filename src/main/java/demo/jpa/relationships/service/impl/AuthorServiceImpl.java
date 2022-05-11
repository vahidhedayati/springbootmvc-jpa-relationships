package demo.jpa.relationships.service.impl;

import demo.jpa.relationships.dto.AddAuthorDto;
import demo.jpa.relationships.dto.AddAuthorToBook;
import demo.jpa.relationships.entity.Author;
import demo.jpa.relationships.entity.Book;
import demo.jpa.relationships.repository.AuthorRepository;
import demo.jpa.relationships.service.AuthorService;
import demo.jpa.relationships.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookService bookService;

    @Override
    public Map<String ,Object> assignAuthorToBook(Long authorId, Long bookId) {
        Map<String ,Object> result = new HashMap<>();
        Optional<Author> authorFound = findById(authorId);
        List<FieldError> message = new ArrayList<>();
        if (authorFound.isPresent()) {
            Author author = authorFound.get();
            Optional<Book> bookFound = bookService.findById(bookId);
            if (bookFound.isPresent()) {
                addAuthorToBook(author, bookFound.get());
            } else {
                message.add(new FieldError("author", "id", "author id was not found"));
                result.put("error", message);
            }
            result.put("author", author);
        } else {
            message.add(new FieldError("author", "id", "author id was not found"));
            result.put("error", message);
        }
        return result;
    }

    @Override
    public Map<String ,Object> assignAuthorToBook(Long authorId, Long bookId, BindingResult bindingResult) {
        Map<String ,Object> result = new HashMap<>();
        Optional<Author> authorFound = findById(authorId);
        if (authorFound.isPresent()) {
            Author author = authorFound.get();
            Optional<Book> bookFound = bookService.findById(bookId);
            if (bookFound.isPresent()) {

                addAuthorToBook(author, bookFound.get());
            } else {
                bindingResult.addError(new FieldError("author", "id", "book id was not found"));
                result.put("error", bindingResult);
            }
            result.put("author", author);
        } else {
            bindingResult.addError(new FieldError("author", "id", "author id was not found"));
            result.put("error", bindingResult);
        }
        return result;
    }

    public Map<String ,Object> assignAuthorToBook(AddAuthorToBook authorToBook, BindingResult bindingResult) {
        Map<String ,Object> result = new HashMap<>();
        Optional<Author> authorFound = findById(authorToBook.getAuthorId());
        if (authorFound.isPresent()) {
            Author author = authorFound.get();
            Optional<Book> bookFound = bookService.findById(authorToBook.getBookId());
            if (bookFound.isPresent()) {
                addAuthorToBook(author, bookFound.get());
            } else {
                bindingResult.addError(new FieldError("authorToBook", "bookId", "book id was not found"));
                result.put("error", bindingResult);
            }
            result.put("author", author);
        } else {
            bindingResult.addError(new FieldError("authorToBook", "authorId", "author id was not found"));
            result.put("error", bindingResult);
        }
        return result;
    }

    private void   addAuthorToBook(Author author, Book book) {
        book.addAuthor(author);
        bookService.save(book);
    }

    @Override
    public <S extends Author> S save(S entity) {
        return authorRepository.save(entity);
    }

    @Override
    public <S extends Author> Iterable<S> saveAll(Iterable<S> entities) {
        return authorRepository.saveAll(entities);
    }

    @Override
    public Optional<Author> findById(Long aLong) {
        return authorRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return authorRepository.existsById(aLong);
    }

    @Override
    public Iterable<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Iterable<Author> findAllById(Iterable<Long> longs) {
        return authorRepository.findAllById(longs);
    }

    @Override
    public long count() {
        return authorRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        authorRepository.deleteById(aLong);
    }

    @Override
    public void delete(Author entity) {
        authorRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        authorRepository.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Author> entities) {
        authorRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        authorRepository.deleteAll();
    }

    public Author save(AddAuthorDto authorDto) {
        Author author=new Author();
        author.setName(authorDto.getName());
        return authorRepository.save(author);
    }
}
