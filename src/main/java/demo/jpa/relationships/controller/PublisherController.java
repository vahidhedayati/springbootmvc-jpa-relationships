package demo.jpa.relationships.controller;

import demo.jpa.relationships.dto.*;
import demo.jpa.relationships.entity.Author;
import demo.jpa.relationships.entity.Book;
import demo.jpa.relationships.entity.Publisher;
import demo.jpa.relationships.service.BookService;
import demo.jpa.relationships.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("publishers")
public class PublisherController {

    @Autowired
    PublisherService publisherService;

    @GetMapping
    List<Publisher> findAll() {
        return publisherService.findAll();
    }

    @PostMapping("/")
    ResponseEntity<?> addPublisher(@Valid @RequestBody AddPublisherDto publisherDto, BindingResult bindingResult) {
        Publisher publisher =  publisherService.save(publisherDto);
        RestResponseBody restResponseBody = new RestResponseBody();
        if (bindingResult.hasErrors()) {
            restResponseBody.setMsg(bindingResult.getAllErrors()
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(restResponseBody);

        }
        return ResponseEntity.ok(publisher);
    }

    @PostMapping("/addPublisherToBook")
    ResponseEntity<?> assignPublisherToBook(@Valid @RequestBody AddPublisherToBook publisherToBook, BindingResult bindingResult) {
        Map<String ,Object> result =  publisherService.assignPublisherToBook(publisherToBook,bindingResult);
        BindingResult errors = (BindingResult) result.get("error");
        RestResponseBody restResponseBody = new RestResponseBody();
        if (errors !=null && errors.hasErrors()) {
            restResponseBody.setMsg(errors.getAllErrors()
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(restResponseBody);

        }
        restResponseBody.setMsg("success");
        restResponseBody.setResult(List.of(((Publisher) result.get("publisher"))));
        return ResponseEntity.ok(restResponseBody);
    }

}
