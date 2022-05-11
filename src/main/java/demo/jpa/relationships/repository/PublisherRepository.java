package demo.jpa.relationships.repository;

import demo.jpa.relationships.dto.AddPublisherDto;
import demo.jpa.relationships.dto.AddPublisherToBook;
import demo.jpa.relationships.entity.Publisher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

import java.util.Map;

@Repository("currentPublisherRepository")
public interface PublisherRepository extends CrudRepository<Publisher,Long> {

    Publisher save(AddPublisherDto entity);

    //Map<String ,Object> assignPublisherToBook(AddPublisherToBook publisherToBook, BindingResult bindingResult);
}
