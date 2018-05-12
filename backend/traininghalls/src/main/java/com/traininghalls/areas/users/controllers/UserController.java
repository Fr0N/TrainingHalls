package com.traininghalls.areas.users.controllers;

import com.google.gson.Gson;
import com.traininghalls.areas.users.models.RegisterUserBindingModel;
import com.traininghalls.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final UserService userService;

    private final Gson gson;

    @Autowired
    public UserController(UserService userService, Gson gson) {
        this.userService = userService;
        this.gson = gson;
    }

    @PostMapping(value = "/api/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserBindingModel user) {
        if(this.userService.userExists(user.getUsername())) {
            return new ResponseEntity<>(this.gson.toJson("User already exists."), HttpStatus.BAD_REQUEST);
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return new ResponseEntity<>("Password and Confirm Password does not match.", HttpStatus.BAD_REQUEST);
        }

        if(this.userService.register(user)) {
            return new ResponseEntity<>(this.gson.toJson("Successfully registered user."), HttpStatus.OK);
        }

        return new ResponseEntity<>("Something went wrong while processing your request...", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/api/current")
    public @ResponseBody
    String getCurrentRoles() {
        String result = "";

        List<GrantedAuthority> authorities = this.userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()).getAuthorities().stream().map(x -> (GrantedAuthority)x).collect(Collectors.toList());

        for (GrantedAuthority authority : authorities) {
            result += authority.getAuthority();
        }

        return result;
    }
}
