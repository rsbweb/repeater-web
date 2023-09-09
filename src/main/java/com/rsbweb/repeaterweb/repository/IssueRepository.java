package com.rsbweb.repeaterweb.repository;

import com.rsbweb.repeaterweb.model.IssueDetails;
import com.rsbweb.repeaterweb.model.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends CrudRepository<IssueDetails,String> {
}
