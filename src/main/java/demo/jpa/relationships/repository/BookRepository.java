package demo.jpa.relationships.repository;

import demo.jpa.relationships.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
