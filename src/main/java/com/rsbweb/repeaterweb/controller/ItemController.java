package com.rsbweb.repeaterweb.controller;

import com.rsbweb.repeaterweb.model.IssueDetails;
import com.rsbweb.repeaterweb.service.IssueService;
import com.rsbweb.repeaterweb.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ItemController {

    @Autowired
    IssueService issueService;

    @Autowired
    UserService userService;

    @GetMapping("/api/issues/getAll")
    public List<IssueDetails> getAllIssues(){
        return issueService.getAllIssueDetails();
    }

    @GetMapping("/api/issues/getIssue")
    public IssueDetails getSpecificIssueById(@RequestParam("issueId")String issueId){
        return issueService.getIssueDetails(issueId);
    }

    @PostMapping("/api/issues/updateIssue")
    public Map<String,Object> updateIssue(@RequestBody IssueDetails issueDetails,@RequestHeader("userId") String userId){
        Map<String,Object> response = new HashMap<>();
        response.put("isSuccess",false);
        if(userService.verifyAdminUser(userId)){
            response.put("issueDetails",issueService.updateIssueDetails(issueDetails));
            response.put("isSuccess",true);
        }else{
            response.put("message","User does not have access to add Issue");
        }
        return response;
    }

    @PutMapping("/api/issues/updateAssignee")
    public Map<String,Object> updateAssignee(@RequestHeader("issueId") String issueId,@RequestHeader("userId") String userId){
        Map<String,Object> response = new HashMap<>();
        response.put("isSuccess",false);
        IssueDetails issueDetails = issueService.updateAssignee(issueId, userId);
        if(issueDetails!=null){
            response.put("isSuccess",true);
            response.put("issueDetails",issueDetails);
        }
        return response;
    }

    @PutMapping("/api/issues/updateStatus")
    public Map<String,Object> updateStatus(@RequestHeader("issueId") String issueId,@RequestHeader("status") String status){
        Map<String,Object> response = new HashMap<>();
        response.put("isSuccess",false);
        IssueDetails issueDetails = issueService.updateStatus(issueId, status);
        if(issueDetails!=null){
            response.put("isSuccess",true);
            response.put("issueDetails",issueDetails);
        }
        return response;
    }
}
