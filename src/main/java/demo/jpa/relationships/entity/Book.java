package demo.jpa.relationships.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name="publisher_id", referencedColumnName = "id")
    private Publisher publisher;

    @ManyToMany
    @JoinTable(
            name="book_author",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="author_id")
    )
    Set<Author> authorsOfBook = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Author> getAuthorsOfBook() {
        return authorsOfBook;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void addAuthor(Author author) {
        this.authorsOfBook.add(author);
    }
}
