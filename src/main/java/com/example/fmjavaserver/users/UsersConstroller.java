package com.example.fmjavaserver.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.lang.StringBuilder;

import com.example.fmjavaserver.LoginRequest;
import java.util.*;

@RestController
@RequestMapping(path = "api/users")

public class UsersConstroller {
    private final UsersService usersService;

    public UsersConstroller(UsersService usersService) {
        this.usersService = usersService;
    }

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/login")
   public Map<String, String> login(@RequestBody LoginRequest loginRequest) {

    String username = loginRequest.getUsername();
    String password = loginRequest.getPassword();
    System.out.println("Logging in now");
    Map<String, String> response = new HashMap<>();
    Optional<Users> userOptional = usersRepository.findByusername(username);
    if (userOptional.isPresent()) {
        Users user = userOptional.get();
        if (user.getPassword().equals(password)) {
            response.put("username", user.getUsername());
            response.put("login", "passed");
        } else {
            response.put("login", "failed");
        }
    } else {
        response.put("login", "failed");
        response.put("error", "usernotfound");
    }

    return response;
}


    @GetMapping
    public List<Users> manageUsers(
        @RequestParam(required = false) String action,
        @RequestParam(required = false) String username,
        @RequestParam(required = false) String id,
        @RequestParam(required = false) String password,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String admin
        
        ) {
            Long validId = (id == null || "undefined".equals(id)) ? null : Long.parseLong(id);
            String validAction= ("undefined".equals(action)) ? null : action;
            String validUserName = ("undefined".equals(username)) ? null : username;
            String validPassword = ("undefined".equals(password)) ? null : password;
            String validEmail = ("undefined".equals(email)) ? null : email;
            String validAdmin= ("undefined".equals(admin)) ? null : admin;
    
        return usersService.manageUsers(validId, validAction, validUserName, validPassword, validEmail, validAdmin);

}

}

