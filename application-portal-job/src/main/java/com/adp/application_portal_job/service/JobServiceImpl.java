package com.adp.application_portal_job.service;

import com.adp.application_portal_job.model.Job;
import com.adp.application_portal_job.repository.JobRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class JobServiceImpl implements JobService{
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(String jobId) {
        jobRepository.deleteBId(jobId);
    }

    @Override
    public Job updateJob(String jobId, Job newJob) {
        Query query = new Query(Criteria.where("_id").is(jobId));

        // Create an Update object to set new values
        Update update = new Update();
        update.set("managerId", newJob.getManagerId()); // Set fields from the new document
        update.set("listingTitle", newJob.getListingTitle());
        update.set("dateListed", newJob.getDateListed());
        update.set("dateClosed", newJob.getDateClosed());
        update.set("jobTitle", newJob.getJobTitle());
        update.set("jobDescription", newJob.getJobDescription());
        update.set("selectedCandidateId", newJob.getSelectedCandidateId());
        update.set("open", newJob.isOpenStatus());

         return mongoTemplate.findAndModify(
                query,
                update,
                 FindAndModifyOptions.options().returnNew(true),
                Job.class
        );
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
