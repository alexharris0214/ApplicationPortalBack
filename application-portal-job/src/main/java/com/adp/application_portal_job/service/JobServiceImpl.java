package com.adp.application_portal_job.service;

import com.adp.application_portal_job.model.Job;

public class JobServiceImpl implements JobService{
    @Override
    public Job createJob(Job job) {
        return null;
    }

    @Override
    public void deleteJob(String jobId) {

    }

    @Override
    public void updateJob(Job job) {

    }

    @Override
    public void getAllJobsForManager(String managerId) {

    }

    @Override
    public void markJobAsComplete(String jobId) {

    }

    @Override
    public void selectCandidateForJob(String jobId, String candidateId) {

    }

    @Override
    public void getJobsCandidateAppliedTo(String candidateId) {

    }

    @Override
    public void getJobsCandidateAcceptedTo(String candidateId) {

    }

    @Override
    public void getJobsCandidateDeniedTo(String candidateId) {

    }
}
