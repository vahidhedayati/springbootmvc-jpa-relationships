package demo.jpa.relationships.repository;

import demo.jpa.relationships.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
