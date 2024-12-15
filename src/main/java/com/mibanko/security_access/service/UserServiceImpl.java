package com.mibanko.security_access.service;

import com.mibanko.security_access.entity.Authority;
import com.mibanko.security_access.entity.User;
import com.mibanko.security_access.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public List<User> getListUsers() {
        return userRepo.findAll();
    }


    @Override
    public User getUserByUsername(String username) {
        Optional<User> byId = userRepo.findById(username);
        if(byId.isPresent()) return byId.get();
        throw new RuntimeException("user not found");
    }


    @Override
    @Transactional
    public User updateUser(User user) {
        return userRepo.save(user);
    }

    @Override
    @Transactional
    public User registerUser(User user) {
        user.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        user.setActive(true);
        return userRepo.save(user);
    }

    @Override
    @Transactional
    public User removeUser(String username) {
        Optional<User> usedDeleted = userRepo.findById(username);
        if(usedDeleted.isPresent()) userRepo.deleteById(username);
        throw new RuntimeException("user not found");
    }

    @Override
    public User setAuthority(String username, Authority authorities) {
        Optional<User> user = userRepo.findById(username);
        if (user.isPresent()){
          user.get().setAuthority(new ArrayList<>(Collections.singleton(authorities)));
            return user.get();
        }
        throw new RuntimeException("User not found");

    }

    @Override
    public String verify(User user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        return jwtService.generateToken(user.getUsername());
    }


}
