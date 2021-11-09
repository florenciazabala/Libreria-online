package com.egg.library.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;

@Component
@Data
@NoArgsConstructor
public class UserVO {

    private Integer id;
    private String username;
    @Email
    private String mail;
    private String password;
    private Boolean discharged;

}
