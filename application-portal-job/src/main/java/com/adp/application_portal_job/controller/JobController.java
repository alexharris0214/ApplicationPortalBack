package com.adp.application_portal_job.controller;

import com.adp.application_portal_job.model.Job;
import com.adp.application_portal_job.service.JobService;
import com.adp.application_portal_job.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        String mangerId = jwtService.extractUsername(headers.getFirst("Authorization"));
        List<Job> jobsForManager = jobService.getAllJobsForManager(mangerId);
        return ResponseEntity.ok(jobsForManager);
    }

    @PostMapping("/create-job")
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job createdJob = jobService.createJob(job);
        return ResponseEntity.ok(createdJob);
    }

    @DeleteMapping("/delete-job")
    public ResponseEntity<String> deleteJob(@RequestBody String jobId) {
        jobService.deleteJob(jobId);
        return ResponseEntity.ok("Successfully Deleted Job with JobID: " + jobId);
    }

    @PatchMapping("/update-job")
    public ResponseEntity<Job> updateJob(@RequestBody String jobId, Job job) {
        Job updatedJob = jobService.updateJob(jobId, job);
        return ResponseEntity.ok(updatedJob);
    }

    @PatchMapping("/job-open")
    public ResponseEntity<String> markJobAsOpen(@RequestBody String jobId) {
        jobService.markJobAsOpen(jobId);
        return ResponseEntity.ok("Marked Job with ID: " + jobId + " as open");
    }

    @PatchMapping("job-closed")
    public ResponseEntity<String> markJobAsClosed(@RequestBody String jobId) {
        jobService.markJobAsClosed(jobId);
        return ResponseEntity.ok("Marked Job with ID: " + jobId + " as closed");
    }

    @PatchMapping("select-candidate")
    public ResponseEntity<String> selectCandidate(@RequestBody String jobId, String candidateId) {
        jobService.selectCandidateForJob(jobId, candidateId);
        return ResponseEntity.ok("Marked Job with ID: " + jobId + " as open");
    }
}
