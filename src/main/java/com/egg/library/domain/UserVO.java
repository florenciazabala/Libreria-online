package com.egg.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

    private Integer id;
    private String username;
    @Email
    private String mail;
    private String password;
    private Boolean discharged;

    @Nullable
    private List<RolVO> roles;

}
