package com.mibanko.security_access.service;

import com.mibanko.security_access.entity.Authority;
import com.mibanko.security_access.entity.User;

import java.util.List;

public interface AuthorityService {
    List<Authority> getListAuthorities();
    Authority getAuthorityById(int id);
    List<Authority> getAuthorityByUsername(String username);
    Authority updateAuthority(Authority authority);
    Authority registerAuthority(Authority authority);
    Authority removeAuthority(int id);


}
