package demo.jpa.relationships.service;

import demo.jpa.relationships.dto.AddAuthorDto;
import demo.jpa.relationships.dto.AddAuthorToBook;
import demo.jpa.relationships.entity.Author;
import demo.jpa.relationships.repository.AuthorRepository;
import org.springframework.validation.BindingResult;

import java.util.Map;

public interface AuthorService extends AuthorRepository  {

    Map<String ,Object> assignAuthorToBook(Long authorId, Long bookId);
    Map<String ,Object> assignAuthorToBook(Long authorId, Long bookId, BindingResult bindingResult);
    Map<String ,Object> assignAuthorToBook(AddAuthorToBook authorToBook, BindingResult bindingResult);
    Author save(AddAuthorDto authorDto);
}
