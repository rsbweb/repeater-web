package com.rsbweb.repeaterweb.controller;

import com.rsbweb.repeaterweb.constants.StatusConstants;
import com.rsbweb.repeaterweb.model.IssueDetails;
import com.rsbweb.repeaterweb.service.IssueService;
import com.rsbweb.repeaterweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IssueController {

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

    @GetMapping("/api/issues/getIssueBasedOnUser")
    public  List<IssueDetails>  getIssueBasedOnUser(@RequestHeader("userId")String userId){
        return issueService.getIssueDetailsByUser(userId);
    }

    @PostMapping("/api/issues/updateIssue")
    public Map<String,Object> updateIssue(@RequestBody IssueDetails issueDetails,@RequestHeader("userId") String userId){
        Map<String,Object> response = new HashMap<>();
        response.put("isSuccess",false);
        if(userService.verifyAdminUser(userId)){
            IssueDetails responseDetails = issueService.updateIssueDetails(issueDetails);
            response.put("issueDetails", responseDetails);
            response.put("message",String.format("Issue has been added. ID : %s",responseDetails.issueId));
            response.put("isSuccess",true);
        }else{
            response.put("message","User does not have access to add Issue");
        }
        return response;
    }

    @GetMapping("/api/issues/updateAssignee")
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

    @PostMapping("/api/issues/updateStatus")
    public Map<String,Object> updateStatus(@RequestHeader("issueId") String issueId,@RequestHeader("status") String status,@RequestBody(required = false) IssueDetails issueDetailsRequest){
        Map<String,Object> response = new HashMap<>();
        response.put("isSuccess",false);
        IssueDetails issueDetails = issueService.updateStatus(issueId, status,issueDetailsRequest);

        if(issueDetails!=null){
            response.put("isSuccess",true);
            response.put("issueDetails",issueDetails);
        }
        return response;
    }
}
