package demo.jpa.relationships.controller;

import demo.jpa.relationships.ResponseTransfer;
import demo.jpa.relationships.dto.AddAuthorDto;
import demo.jpa.relationships.dto.AddAuthorToBook;
import demo.jpa.relationships.dto.RestResponseBody;
import demo.jpa.relationships.entity.Author;
import demo.jpa.relationships.service.impl.AuthorServiceImpl;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("authors")
public class AuthorController {

    @Autowired
    AuthorServiceImpl authorService;

    @GetMapping
    Iterable<Author> findAll() {
        return authorService.findAll();
    }


    @PostMapping("/databinder")
    @RequestMapping(method = RequestMethod.POST)
    public Author insert(@RequestBody Map values) throws BindException {

        Author author = new Author();

        DataBinder binder = new DataBinder(author);
        binder.bind(new MutablePropertyValues(values));

        // validator is instance of LocalValidatorFactoryBean class
        // binder.setValidator(validator);
        binder.validate();

        // throws BindException if there are binding/validation
        // errors, exception is handled using @ControllerAdvice.
        binder.close();

        // No binding/validation errors, profile is populated
        // with request values.
        return null;
    }


    @GetMapping(value="/assignAuthorToBook/{authorId}/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> assignAuthorToBook(@Valid @PathVariable Long authorId, @PathVariable Long bookId) {
        Map<String ,Object> result =  authorService.assignAuthorToBook(authorId,bookId);
        List<FieldError> errors = ( List<FieldError>) result.get("error");
        RestResponseBody restResponseBody = new RestResponseBody();
        if (errors.size()>0) {
            restResponseBody.setMsg(errors
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(restResponseBody);

        }
        restResponseBody.setMsg("success");
        restResponseBody.setResult(List.of(((Author) result.get("author"))));
        return ResponseEntity.ok(restResponseBody);
    }


    @GetMapping("/assignAuthor2Book/{authorId}/{bookId}")
    ResponseEntity<?> assignAuthor2Book(@Valid @PathVariable Long authorId, @PathVariable Long bookId, Author author, BindingResult bindingResult) {
        Map<String ,Object> result =  authorService.assignAuthorToBook(authorId,bookId,bindingResult);
        BindingResult errors = (BindingResult) result.get("error");
        RestResponseBody restResponseBody = new RestResponseBody();
        if (errors.hasErrors()) {
            restResponseBody.setMsg(errors.getAllErrors()
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(restResponseBody);

        }
        restResponseBody.setMsg("success");
        restResponseBody.setResult(List.of(((Author) result.get("author"))));
        return ResponseEntity.ok(restResponseBody);
    }

    @PostMapping("/addAuthBook")
    //@RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> assignAuthorBook(@Valid @RequestBody AddAuthorToBook authorToBook, BindingResult bindingResult) {
        Map<String ,Object> result =  authorService.assignAuthorToBook(authorToBook,bindingResult);
        BindingResult errors = (BindingResult) result.get("error");
        RestResponseBody restResponseBody = new RestResponseBody();
        if (errors.hasErrors()) {
            restResponseBody.setMsg(errors.getAllErrors()
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(restResponseBody);

        }
        restResponseBody.setMsg("success");
        restResponseBody.setResult(List.of(((Author) result.get("author"))));
        return ResponseEntity.ok(restResponseBody);
    }

    @PostMapping("/")
    ResponseEntity<?> addAuthor(@Valid @RequestBody AddAuthorDto authorDto, BindingResult bindingResult) {
        Author author =  authorService.save(authorDto);
        RestResponseBody restResponseBody = new RestResponseBody();
        if (bindingResult.hasErrors()) {
            restResponseBody.setMsg(bindingResult.getAllErrors()
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(restResponseBody);

        }
        //restResponseBody.setMsg("success");
        //restResponseBody.setResult(List.of(author));
        return ResponseEntity.ok(author);
    }

}
