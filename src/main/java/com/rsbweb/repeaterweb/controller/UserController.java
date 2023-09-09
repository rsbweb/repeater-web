package com.rsbweb.repeaterweb.controller;

import com.rsbweb.repeaterweb.model.UserDetails;
import com.rsbweb.repeaterweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/api/users/updateBulkUserDetails")
    public List<UserDetails> updateBulkUserDetails(@RequestBody List<UserDetails> userDetailsList) {
        return userService.updateBulkUserDetails(userDetailsList);
    }

    @PostMapping("/api/users/updateUserDetails")
    public UserDetails updateBulkUserDetails(@RequestBody  UserDetails userDetailsList) {
        return userService.updateUserDetails(userDetailsList);
    }

    @GetMapping("/api/users/getAllUsers")
    public List<UserDetails> getAllUsers() {
        return userService.getAllUserDetails();
    }
    @GetMapping("/api/users/getUser")
    public UserDetails getUser(@RequestParam("userName") String userName) {
        return userService.getUserDetails(userName);
    }
}
