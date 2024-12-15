package com.mibanko.security_access.config;

import com.mibanko.security_access.entity.User;
import com.mibanko.security_access.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    public MyUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws RuntimeException {
        Optional<User> user = userRepo.findById(username);
        if(user.isPresent()) return new MyUserDetails(user.get());
        throw new RuntimeException("user not found, type another");

    }

}
