package demo.jpa.relationships.repository;

import demo.jpa.relationships.dto.AddBookDto;
import demo.jpa.relationships.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("currentBookRepository")
public interface BookRepository extends CrudRepository<Book, Long> {


    Book save(AddBookDto addBookDto);
}
