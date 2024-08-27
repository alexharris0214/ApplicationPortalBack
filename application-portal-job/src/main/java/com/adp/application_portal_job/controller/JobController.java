package com.adp.application_portal_job.controller;

import com.adp.application_portal_job.model.Job;
import com.adp.application_portal_job.model.requests.JobIdRequest;
import com.adp.application_portal_job.model.requests.SelectCandidateRequest;
import com.adp.application_portal_job.model.requests.UpdateJobRequest;
import com.adp.application_portal_job.service.JobService;
import com.adp.application_portal_job.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/jobs")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/open-jobs")
    public ResponseEntity<List<Job>> getAllOpenJobs(@RequestHeader HttpHeaders headers) {
        List<Job> jobsOpen = jobService.getAllOpenJobs();
        return ResponseEntity.ok(jobsOpen);
    }

    @GetMapping("/closed-jobs")
    public ResponseEntity<List<Job>> getAllClosedJobs(@RequestHeader HttpHeaders headers) {
        List<Job> jobsClosed = jobService.getAllClosedJobs();
        return ResponseEntity.ok(jobsClosed);
    }

    @GetMapping("/manager-jobs")
    public ResponseEntity<List<Job>> getAllJobsForManager(@RequestHeader HttpHeaders headers) {
        String token = Objects.requireNonNull(headers.getFirst("Authorization")).substring(7);
        String mangerId = jwtService.extractUsername(token);
        List<Job> jobsForManager = jobService.getAllJobsForManager(mangerId);
        return ResponseEntity.ok(jobsForManager);
    }

    @PostMapping("/create-job")
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job createdJob = jobService.createJob(job);
        return ResponseEntity.ok(createdJob);
    }

    @DeleteMapping("/delete-job/{jobId}")
    public ResponseEntity<String> deleteJob(@PathVariable String jobId) {
        jobService.deleteJob(jobId);
        return ResponseEntity.ok("Successfully Deleted Job with JobID: " + jobId);
    }

    @PutMapping("/update-job")
    public ResponseEntity<Job> updateJob(@RequestBody UpdateJobRequest request) {
        Job updatedJob = jobService.updateJob(request.getJobId(), request.getNewJob());
        return ResponseEntity.ok(updatedJob);
    }

    @PatchMapping("/job-open")
    public ResponseEntity<String> markJobAsOpen(@RequestBody JobIdRequest jobIdRequest) {
        jobService.markJobAsOpen(jobIdRequest.getJobId());
        return ResponseEntity.ok("Marked Job with ID: " + jobIdRequest.getJobId() + " as open");
    }

    @PatchMapping("job-closed")
    public ResponseEntity<String> markJobAsClosed(@RequestBody JobIdRequest jobIdRequest) {
        jobService.markJobAsClosed(jobIdRequest.getJobId());
        return ResponseEntity.ok("Marked Job with ID: " + jobIdRequest.getJobId() + " as closed");
    }

    @PatchMapping("select-candidate")
    public ResponseEntity<String> selectCandidate(@RequestBody SelectCandidateRequest selectCandidateRequest) {
        jobService.selectCandidateForJob(selectCandidateRequest.getJobId(), selectCandidateRequest.getCandidateId());
        return ResponseEntity.ok("Marked Job with ID: " + selectCandidateRequest.getJobId() + " as closed with Candidate ID:" + selectCandidateRequest.getCandidateId());
    }
}
