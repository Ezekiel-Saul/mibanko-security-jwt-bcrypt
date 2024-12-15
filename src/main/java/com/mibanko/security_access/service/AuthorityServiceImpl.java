package com.mibanko.security_access.service;

import com.mibanko.security_access.entity.Authority;
import com.mibanko.security_access.repo.AuthorityRepo;
import com.mibanko.security_access.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorityServiceImpl implements AuthorityService{


    private final AuthorityRepo authorityRepo;
    private final UserRepo userRepo;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepo authorityRepo, UserRepo userRepo) {
        this.authorityRepo = authorityRepo;
        this.userRepo = userRepo;
    }

    @Override
    public List<Authority> getListAuthorities() {
        return authorityRepo.findAll();
    }

    @Override
    public Authority getAuthorityById(int id) {
        Optional<Authority> byId = authorityRepo.findById(id);
        if(byId.isPresent()) return byId.get();
        throw new RuntimeException("Invalid ID number");
    }

    @Override
    public List<Authority> getAuthorityByUsername(String username) {
        List<Authority> authority = userRepo.findById(username).get().getAuthority();
        if(authority != null) return authority;
        throw new RuntimeException("user withour authorities");
    }


    @Override
    @Transactional
    public Authority updateAuthority(Authority authority) {
        return authorityRepo.save(authority);
    }

    @Override
    @Transactional
    public Authority registerAuthority(Authority authority) {
        return authorityRepo.save(authority);
    }

    @Override
    @Transactional
    public Authority removeAuthority(int id) {
        Optional<Authority> byId = authorityRepo.findById(id);
        if(byId.isPresent()) authorityRepo.deleteById(id);
        throw new RuntimeException("Invalid ID number");
    }

}
