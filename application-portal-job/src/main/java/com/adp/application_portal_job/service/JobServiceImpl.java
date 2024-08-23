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

import java.util.List;

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
    public List<Job> getAllJobsForManager(String managerId) {
        Query getAllJobsForManagerQuery  = new Query(Criteria.where("managerId").is(managerId));
        return mongoTemplate.find(getAllJobsForManagerQuery, Job.class, "jobs");
    }

    @Override
    public List<Job> getAllOpenJobs() {
        Query getAllOpenJobs = new Query(Criteria.where("openStatus").is(true));
        return mongoTemplate.find(getAllOpenJobs, Job.class, "jobs");
    }

    @Override
    public List<Job> getAllClosedJobs() {
        Query getAllClosedJobs = new Query(Criteria.where("openStatus").is(false));
        return mongoTemplate.find(getAllClosedJobs, Job.class, "jobs");
    }

    @Override
    public void markJobAsOpen(String jobId) {
        Query query = new Query(Criteria.where("_id").is(jobId));

        // Create an Update object to set new values
        Update update = new Update();
        update.set("open", true);

        mongoTemplate.findAndModify(
                query,
                update,
                Job.class
        );
    }

    @Override
    public void markJobAsClosed(String jobId) {
        Query query = new Query(Criteria.where("_id").is(jobId));

        // Create an Update object to set new values
        Update update = new Update();
        update.set("open", false);

        mongoTemplate.findAndModify(
                query,
                update,
                Job.class
        );
    }

    @Override
    public void selectCandidateForJob(String jobId, String candidateId) {
        Query query = new Query(Criteria.where("_id").is(jobId));

        // Create an Update object to set new values
        Update update = new Update();
        update.set("selectedCandidateId", candidateId);

        mongoTemplate.findAndModify(
                query,
                update,
                Job.class
        );
    }

    @Override
    public List<Job> getJobsCandidateAppliedTo(String candidateId) {

        return List.of();
    }

    @Override
    public List<Job> getJobsCandidateAcceptedTo(String candidateId) {
        Query getAllJobsForManagerQuery  = new Query(Criteria.where("selectedCandidateId").is(candidateId));
        return mongoTemplate.find(getAllJobsForManagerQuery, Job.class, "jobs");
    }

    @Override
    public List<Job> getJobsCandidateDeniedTo(String candidateId) {
        return List.of();
    }


}
