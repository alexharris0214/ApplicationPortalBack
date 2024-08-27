package com.adp.application_portal_job.model.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectCandidateRequest {
    private String jobId;
    private String candidateId;
}
