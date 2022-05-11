package demo.jpa.relationships.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddAuthorToBook {

     Long authorId;
     Long bookId;

/*
    public AddAuthorToBook(@JsonProperty("authorId") Long authorId,@JsonProperty("authorId") Long bookId) {
        this.authorId = authorId;
        this.bookId = bookId;
    }
*/

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
