package com.egg.library.domain.service;

import com.egg.library.domain.UserVO;
import com.egg.library.domain.repository.UserRepository;
import com.egg.library.exeptions.FieldAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.NoSuchElementException;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    UserVO user;

    @Transactional
    public UserVO create(String username,String mail,String password){

        if(userRepository.findByMail(mail).isPresent()){
            throw new FieldAlreadyExistException("There is an existing account associated with this email address");
        }
        if(userRepository.findByUsername(username).isPresent()){
           throw new FieldAlreadyExistException("The username already exists");
        }

        user = new UserVO();
        setDates(user,username,mail,password);
        return userRepository.create(user);
    }

    @Transactional
    public void update(String username,String mail,String password){
        user= userRepository.findByMail(mail)
                .orElseThrow(() -> new NoSuchElementException("The user '"+mail+"' doesn't exists"));
        setDates(user,username,mail,password);
        userRepository.update(user);
    }

    public void setDates(UserVO user,String username,String mail,String password){
        user.setUsername(username);
        user.setMail(mail);
        user.setPassword(encoder.encode(password));
    }

    private final String MESSAGE = "The username doesn't exists %s";
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO userVO = userRepository.findByMailOrUsername(username,username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(MESSAGE, username)));
        return new User(userVO.getUsername(),userVO.getPassword(), Collections.emptyList());
    }

    public UserVO findByMail(String mail){
        return userRepository.findByMail(mail).orElseThrow(
                ()-> new NoSuchElementException("there is no registered user with that email"));
    }
}
