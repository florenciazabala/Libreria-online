package com.egg.library.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

@Component
@Data
@NoArgsConstructor
public class AuthorVO {

    private Integer idAuthor;
    @NotEmpty(message = "Field 'name' cant't be empty")
    private String name;
    private Boolean discharged;
    private Map<Long,String> books;
}
