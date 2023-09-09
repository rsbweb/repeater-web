package com.rsbweb.repeaterweb.service;

import com.rsbweb.repeaterweb.model.IssueDetails;
import com.rsbweb.repeaterweb.model.UserDetails;
import com.rsbweb.repeaterweb.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IssueService {

    @Autowired
    IssueRepository issueRepository;

    public List<IssueDetails> getAllIssueDetails(){
        List<IssueDetails> response = new ArrayList<>();
        Iterable<IssueDetails> issueDetails = issueRepository.findAll();
        issueDetails.forEach(response::add);
        return response;
    }

    public List<IssueDetails> updateBulkIssueDetails(List<IssueDetails> issueDetailsList){
        List<IssueDetails> response = new ArrayList<>();
        Iterable<IssueDetails> issueDetails = issueRepository.saveAll(issueDetailsList);
        issueDetails.forEach(response::add);
        return response;
    }
    public IssueDetails updateIssueDetails(IssueDetails issueDetails){
        return issueRepository.save(issueDetails);
    }

    public IssueDetails getIssueDetails(String issueId){
        Optional<IssueDetails> byId = issueRepository.findById(issueId);
        return byId.orElseGet(IssueDetails::new);
    }

    public IssueDetails updateAssignee(String issueId,String userId){
        IssueDetails issueDetails = issueRepository.findById(issueId).get();
        issueDetails.assignedUserName = userId;
        return issueRepository.save(issueDetails);
    }

    public IssueDetails updateStatus(String issueId,String status){
        IssueDetails issueDetails = issueRepository.findById(issueId).get();
        issueDetails.status = status;
        return issueRepository.save(issueDetails);
    }
}
