package com.adp.application_portal_job.service;

import com.adp.application_portal_job.model.Job;

public interface JobService {
    Job createJob(Job job);
    void deleteJob(String jobId);
    Job updateJob(String jobId, Job job);
    void getAllJobsForManager(String managerId);
    void markJobAsComplete(String jobId);
    void selectCandidateForJob(String jobId, String candidateId);
    void getJobsCandidateAppliedTo(String candidateId);
    void getJobsCandidateAcceptedTo(String candidateId);
    void getJobsCandidateDeniedTo(String candidateId);

}
