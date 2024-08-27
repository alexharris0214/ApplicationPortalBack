package com.adp.application_portal_job.model.requests;

import com.adp.application_portal_job.model.Job;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateJobRequest {
    String jobId;
    Job newJob;
}
