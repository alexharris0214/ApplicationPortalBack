package com.adp.application_portal_job.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "jobs")
public class Job {
    @Id
    private String id;
    private String managerId;
    private String listingTitle;
    private Date dateListed;
    private Date dateClosed;
    private String jobTitle;
    private boolean openStatus;
    private String jobDescription;
    private String selectedCandidateId;

}