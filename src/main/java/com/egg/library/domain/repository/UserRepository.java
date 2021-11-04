package com.egg.library.domain.repository;

import com.egg.library.domain.UserVO;

import java.util.Optional;

public interface UserRepository {
    UserVO create(UserVO userVO);
    void update(UserVO userVO);
    Optional<UserVO> findByMailOrUsername(String mail, String username);
    Optional<UserVO> findByMail(String mail);
    Optional<UserVO> findByUsername(String username);
}
