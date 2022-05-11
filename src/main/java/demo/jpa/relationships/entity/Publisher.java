package demo.jpa.relationships.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "publisher" , cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Book> publishedBooks= new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addNewBook(Book book) {
        this.publishedBooks.add(book);
    }
}
