package com.mibanko.security_access.service;

import com.mibanko.security_access.entity.Authority;
import com.mibanko.security_access.entity.User;

import java.util.List;

public interface UserService {
    List<User> getListUsers();
    User getUserByUsername(String username);
    User updateUser(User user);
    User registerUser(User user);
    User removeUser(String username);
    User setAuthority(String username, Authority authority);
    String verify(User user);
}
