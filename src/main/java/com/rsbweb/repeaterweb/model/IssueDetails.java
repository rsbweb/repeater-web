package com.rsbweb.repeaterweb.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;

import static jakarta.persistence.GenerationType.*;

@Data
@Entity
@Table(name="issueDetails")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueDetails {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    public Long issueId;
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
    public String remarks1;
    public String remarks2;
    public String remarks3;

}
