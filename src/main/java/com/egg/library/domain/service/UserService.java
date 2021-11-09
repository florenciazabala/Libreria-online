package com.egg.library.domain.service;

import com.egg.library.domain.CustomerVO;
import com.egg.library.domain.RolVO;
import com.egg.library.domain.UserVO;
import com.egg.library.domain.repository.UserRepository;
import com.egg.library.exeptions.BadCredentialsException;
import com.egg.library.exeptions.FieldAlreadyExistException;
import com.egg.library.exeptions.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  CustomerService customerService;


    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    UserVO user;

    @Transactional
    public UserVO create(String username, String mail, String password, List<RolVO> roles){

        if(userRepository.findByMail(mail).isPresent()){
            throw new FieldAlreadyExistException("There is an existing account associated with this email address");
        }
        if(userRepository.findByUsername(username).isPresent()){
           throw new FieldAlreadyExistException("The username already exists");
        }

        user = new UserVO();
        setDates(user,username,mail,password,roles);
        return userRepository.create(user);
    }

    @Transactional
    public void update(String username,String mail,String password,List<RolVO> roles){
        user= userRepository.findByMail(mail)
                .orElseThrow(() -> new NoSuchElementException("The user '"+mail+"' doesn't exists"));
        setDates(user,username,mail,password,roles);
        userRepository.update(user);
    }


    private final Boolean DISCHARGE = Boolean.TRUE;
   // private final RolVO ROLEDEFAULT = rolService.findByRole("USER");

    public void setDates(UserVO user,String username,String mail,String password,List<RolVO> roles){
       // if(roles.isEmpty() || roles == null){
       //     roles.add(ROLEDEFAULT);
        //}

        user.setUsername(username);
        user.setMail(mail);
        user.setPassword(encoder.encode(password));
        user.setRoles(roles);
        user.setDischarged(DISCHARGE);
    }

    private final String MESSAGE = "The username doesn't exists %s";
    private final String MESSAGEDISHARGE = "The account is disabled %s";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserVO userVO = userRepository.findByMailOrUsername(username,username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(MESSAGE, username)));
        /*
        if(userVO.getDischarged() == null || userVO.getDischarged() == false){
            throw new UsernameNotFoundException(String.format("The account is disabled", username));
        }*/
        CustomerVO customerVO = customerService.findByUserMail(userVO.getMail());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession  session = attributes.getRequest().getSession(true);
        session.setAttribute("userSession",customerVO);

        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

        for(RolVO rol: userVO.getRoles()){
            roles.add(new SimpleGrantedAuthority(rol.getRole()));
        }

        return new User(userVO.getMail(),userVO.getPassword(), roles);
    }

    public UserVO findByMail(String mail){
        return userRepository.findByMail(mail).orElseThrow(
                ()-> new NoSuchElementException("there is no registered user with that email"));
    }
}
