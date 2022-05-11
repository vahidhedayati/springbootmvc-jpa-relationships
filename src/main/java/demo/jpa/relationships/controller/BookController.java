package demo.jpa.relationships.controller;

import demo.jpa.relationships.dto.AddAuthorDto;
import demo.jpa.relationships.dto.AddBookDto;
import demo.jpa.relationships.dto.RestResponseBody;
import demo.jpa.relationships.entity.Author;
import demo.jpa.relationships.entity.Book;
import demo.jpa.relationships.service.BookService;
import demo.jpa.relationships.service.impl.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    List<Book> findAll() {
        return bookService.findAll();
    }

    @PostMapping("/")
    ResponseEntity<?> addAuthor(@Valid @RequestBody AddBookDto bookDto, BindingResult bindingResult) {
        Book book =  bookService.save(bookDto);
        RestResponseBody restResponseBody = new RestResponseBody();
        if (bindingResult.hasErrors()) {
            restResponseBody.setMsg(bindingResult.getAllErrors()
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(restResponseBody);

        }
        return ResponseEntity.ok(book);
    }
}
