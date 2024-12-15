package com.mibanko.security_access.rest;

import com.mibanko.security_access.entity.Authority;
import com.mibanko.security_access.service.AuthorityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class AuthoritiesRestController {

    @Autowired
    private final AuthorityServiceImpl authorityService;


    public AuthoritiesRestController(AuthorityServiceImpl authorityService) {
        this.authorityService = authorityService;
    }

    @GetMapping("{id}")
    public List<Authority> getAuthoritiesByUserId(@PathVariable String username){
        return this.authorityService.getAuthorityByUsername(username);
    }

    @GetMapping
    public List<Authority> getAuthorities(){
        return this.authorityService.getListAuthorities();
    }
}
