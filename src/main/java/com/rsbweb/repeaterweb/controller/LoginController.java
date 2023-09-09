package com.rsbweb.repeaterweb.controller;

import com.rsbweb.repeaterweb.model.UserDetails;
import com.rsbweb.repeaterweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/api/login")
    public Map<String,Object> performLogin(@RequestBody Map<String,String> creds){
        Map<String,Object> response = new HashMap<>();
        response.put("isSuccess",false);
        String userName = creds.get("username");
        String password = creds.get("password");
        UserDetails userDetails = userService.getUserDetails(userName);
        if(userDetails.userName!=null){
            if(userDetails.password.equals(password)){
                response.put("isSuccess",true);
                response.put("isAdmin",userDetails.isAdmin);
            }else{
                response.put("message","Invalid Password. Please check");
            }
        }else{
            response.put("message","Invalid UserName. Please check");
        }
        return response;
    }
}
