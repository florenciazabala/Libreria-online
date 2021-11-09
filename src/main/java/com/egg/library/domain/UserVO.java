package com.egg.library.domain;

import com.egg.library.perisitence.entity.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private List<RolVO> roles;

}
