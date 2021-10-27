package com.egg.library.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
public class EditorialVO {
    private Integer id;
    @NotEmpty(message = "Field 'name' cant't be empty")
    private String name;
    private Boolean discharged;
    private List<String> books;

}
