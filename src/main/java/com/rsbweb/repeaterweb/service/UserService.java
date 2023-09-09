package com.rsbweb.repeaterweb.service;

import com.rsbweb.repeaterweb.model.UserDetails;
import com.rsbweb.repeaterweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserDetails> updateBulkUserDetails(List<UserDetails> userDetailsList){
        List<UserDetails> response = new ArrayList<>();
        Iterable<UserDetails> userDetails = userRepository.saveAll(userDetailsList);
        userDetails.forEach(response::add);
        return response;
    }
    public UserDetails updateUserDetails(UserDetails userDetailsList){
        return userRepository.save(userDetailsList);
    }

    public List<UserDetails> getAllUserDetails(){
        List<UserDetails> response = new ArrayList<>();
        Iterable<UserDetails> userDetails = userRepository.findAll();
        userDetails.forEach(response::add);
        return response;
    }

    public UserDetails getUserDetails(String userId){
        Optional<UserDetails> byId = userRepository.findById(userId);
        return byId.orElseGet(UserDetails::new);
    }

    public Boolean verifyAdminUser(String userId){
        List<UserDetails> response = new ArrayList<>();
        Iterable<UserDetails> userDetails = userRepository.findAll();
        userDetails.forEach(response::add);
        for(UserDetails details: response){
            if(details.isAdmin && details.userName.equals(userId)){
                return true;
            }
        }
        return false;
    }
}
