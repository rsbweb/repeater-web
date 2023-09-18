package com.rsbweb.repeaterweb.repository;

import com.rsbweb.repeaterweb.model.IssueDetails;
import com.rsbweb.repeaterweb.model.UserDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends CrudRepository<IssueDetails,String> {

    @Query("SELECT i FROM IssueDetails i WHERE i.status = 'CREATED'")
    List<IssueDetails> getCreatedIssues();

    @Query("SELECT i FROM IssueDetails i WHERE i.assignedUserName= ?1 and i.status != 'COMPLETED'")
    List<IssueDetails> getIssuesByUser(String userId);
}
