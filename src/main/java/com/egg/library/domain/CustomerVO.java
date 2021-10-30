package com.egg.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Map;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class CustomerVO {
    private Integer id;
    @NotEmpty
    @Size(min=10000000 , max=99999999)
    private Long document;
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastName;
    @Email
    private String mail;
    private String telephone;
    private Boolean discharged;
    private Map<Integer,String> loans;
}
