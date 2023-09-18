package com.rsbweb.repeaterweb.service;

import com.rsbweb.repeaterweb.constants.CommonConstants;
import com.rsbweb.repeaterweb.constants.StatusConstants;
import com.rsbweb.repeaterweb.model.IssueDetails;
import com.rsbweb.repeaterweb.model.UserDetails;
import com.rsbweb.repeaterweb.repository.IssueRepository;
import com.rsbweb.repeaterweb.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IssueService {

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    CommonUtils commonUtils;

    public List<IssueDetails> getAllIssueDetails(){
        return issueRepository.getCreatedIssues();
    }

    public List<IssueDetails> updateBulkIssueDetails(List<IssueDetails> issueDetailsList){
        List<IssueDetails> response = new ArrayList<>();
        Iterable<IssueDetails> issueDetails = issueRepository.saveAll(issueDetailsList);
        issueDetails.forEach(response::add);
        return response;
    }
    public IssueDetails updateIssueDetails(IssueDetails issueDetails){
        issueDetails.createdTimestamp=commonUtils.getStartTime();
        issueDetails.status= StatusConstants.CREATED;
        return issueRepository.save(issueDetails);
    }

    public IssueDetails getIssueDetails(String issueId){
        Optional<IssueDetails> byId = issueRepository.findById(issueId);
        return byId.orElseGet(IssueDetails::new);
    }

    public  List<IssueDetails> getIssueDetailsByUser(String userId){
        return issueRepository.getIssuesByUser(userId);
    }

    public IssueDetails updateAssignee(String issueId,String userId){
        IssueDetails issueDetails = issueRepository.findById(issueId).get();
        issueDetails.assignedUserName = userId;
        issueDetails.status=StatusConstants.OPEN;
        return issueRepository.save(issueDetails);
    }

    public IssueDetails updateStatus(String issueId,String status,IssueDetails issueDetailsRequest){
        IssueDetails issueDetails = issueRepository.findById(issueId).get();
        issueDetails.status = status;
        if(issueDetails.status.equalsIgnoreCase(StatusConstants.COMPLETED)){
            issueDetails.remarks1=issueDetailsRequest.remarks1;
            issueDetails.remarks2=issueDetailsRequest.remarks2;
            issueDetails.remarks3=issueDetailsRequest.remarks3;
        }else if(issueDetails.status.equalsIgnoreCase(StatusConstants.DROPPED)){
            issueDetails.assignedUserName=null;
            issueDetails.status=StatusConstants.CREATED;
        }
        return issueRepository.save(issueDetails);
    }
}
