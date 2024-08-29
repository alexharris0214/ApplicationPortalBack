package com.adp.application_portal_job.service;

import com.adp.application_portal_job.model.Job;

import java.util.List;

public interface JobService {
    Job getJob(String jobId);
    Job createJob(Job job);
    void deleteJob(String jobId);
    Job updateJob(String jobId, Job job);
    List<Job> getAllJobsForManager(String managerId);
    List<Job> getAllOpenJobs();
    List<Job> getAllClosedJobs();
    void markJobAsOpen(String jobId);
    void markJobAsClosed(String jobId);
    void selectCandidateForJob(String jobId, String candidateId);
    List<Job> getJobsCandidateAppliedTo(String candidateId);
    List<Job> getJobsCandidateAcceptedTo(String candidateId);
    List<Job> getJobsCandidateDeniedTo(String candidateId);
}
