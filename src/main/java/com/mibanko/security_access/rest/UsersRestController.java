package com.mibanko.security_access.rest;

import com.mibanko.security_access.entity.Authority;
import com.mibanko.security_access.entity.User;
import com.mibanko.security_access.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UsersRestController {

    @Autowired
    private final UserServiceImpl userService;

    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);
    @Autowired
    public UsersRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getListUsers();
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        System.out.println(user);
        return userService.verify(user);
    }

    @PostMapping("/register")
    public User addUser(@RequestBody User user){
        user.setPassword(bcrypt.encode(user.getPassword()));
        return userService.registerUser(user);
    }

    @GetMapping("user-one/{username}")
    public User getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }


    @PostMapping("/roles/{username}")
    public User addRoles(@PathVariable String username, @RequestBody Authority roles){
       return userService.setAuthority(username, roles);
    }
}
