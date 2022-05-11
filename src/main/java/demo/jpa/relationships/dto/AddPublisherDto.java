package demo.jpa.relationships.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AddPublisherDto {

    @NotEmpty(message="Publisher name must not be blank")
    @Size(min=3, max=20)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
