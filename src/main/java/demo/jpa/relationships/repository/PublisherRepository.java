package demo.jpa.relationships.repository;

import demo.jpa.relationships.entity.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher,Long> {
}
