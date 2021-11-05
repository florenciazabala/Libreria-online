package com.egg.library.domain;

import com.egg.library.util.Genre;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Map;

@Component
@Data
@NoArgsConstructor
public class BookVO {


    @Size(min = 13,max = 13,message = "'isbn' must be contain 13 digits")
    private Long isbn;
    @NotEmpty(message = "Field 'title' cant't be empty")
    private String title;
    @Max( value = 13,message = "'year' must be contain 14 digits maximum")
    private Integer year;
    @NotEmpty(message = "Field 'genre' cant't be empty")
    private Genre genre;
    @NotEmpty(message = "Field 'copy' cant't be empty")
    private Integer copy;
    @NotEmpty(message = "Field 'loaned copy' cant't be empty")
    private Integer loanedCopy;
    private Integer avaibleCopy;
    private Boolean discharged;
    @NotEmpty(message = "Field 'author' cant't be empty")
    private AuthorVO author;
    @NotEmpty(message = "Field 'editorial' cant't be empty")
    private EditorialVO editorial;
    private Map<Integer,Integer> loans;

}
