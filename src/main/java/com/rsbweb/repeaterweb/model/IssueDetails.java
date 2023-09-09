package com.rsbweb.repeaterweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name="issueDetails")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueDetails {
    @Id
    public String issueId;
    public String activityType;
    public String createdTimestamp;
    public String issueReportedTimestamp;
    public String companyName;
    public String companyAddress;
    public String companyLocation;
    public String customerContactName;
    public String customerContactNumber;
    public String status;
    public String assignedUserName;
}
