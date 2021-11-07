package com.egg.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorVO {

    private Integer idAuthor;
    @NotEmpty(message = "Field 'name' cant't be empty")
    private String name;
    private Boolean discharged;
    private Map<Long,String> books;

    private PictureVO picture;


    public AuthorVO(Integer idAuthor, String name, Boolean discharged) {
        this.idAuthor = idAuthor;
        this.name = name;
        this.discharged = discharged;
    }

}
