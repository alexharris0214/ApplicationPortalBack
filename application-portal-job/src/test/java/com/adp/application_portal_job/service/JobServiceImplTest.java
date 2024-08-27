package com.adp.application_portal_job.service;

import com.adp.application_portal_job.model.Job;
import com.adp.application_portal_job.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class JobServiceImplTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private JobServiceImpl jobService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateJob() {
        Job job = new Job();
        when(jobRepository.save(any(Job.class))).thenReturn(job);

        Job createdJob = jobService.createJob(job);

        verify(jobRepository, times(1)).save(job);
        assertEquals(job, createdJob);
    }

    @Test
    public void testDeleteJob() {
        String jobId = "job123";
        doNothing().when(jobRepository).deleteById(jobId);

        jobService.deleteJob(jobId);

        verify(jobRepository, times(1)).deleteById(jobId);
    }

    @Test
    public void testUpdateJob() {
        String jobId = "job123";
        Job newJob = new Job();
        newJob.setManagerId("manager123");
        newJob.setListingTitle("New Title");
        newJob.setDateListed(new Date());
        newJob.setDateClosed(new Date());
        newJob.setJobTitle("Engineer");
        newJob.setJobDescription("New Description");
        newJob.setSelectedCandidateId("candidate123");
        newJob.setOpenStatus(true);

        when(mongoTemplate.findAndModify(any(Query.class), any(Update.class), any(), eq(Job.class))).thenReturn(newJob);

        Job updatedJob = jobService.updateJob(jobId, newJob);

        verify(mongoTemplate, times(1)).findAndModify(any(Query.class), any(Update.class), any(), eq(Job.class));
        assertEquals(newJob, updatedJob);
    }

    @Test
    public void testGetAllJobsForManager() {
        String managerId = "manager123";
        Job job1 = new Job();
        job1.setManagerId(managerId);
        Job job2 = new Job();
        job2.setManagerId(managerId);
        List<Job> jobs = Arrays.asList(job1, job2);

        when(mongoTemplate.find(any(Query.class), eq(Job.class), anyString())).thenReturn(jobs);

        List<Job> result = jobService.getAllJobsForManager(managerId);

        verify(mongoTemplate, times(1)).find(any(Query.class), eq(Job.class), anyString());
        assertEquals(2, result.size());
        assertEquals(jobs, result);
    }

    @Test
    public void testGetAllOpenJobs() {
        Job job1 = new Job();
        job1.setOpenStatus(true);
        Job job2 = new Job();
        job2.setOpenStatus(true);
        List<Job> jobs = Arrays.asList(job1, job2);

        when(mongoTemplate.find(any(Query.class), eq(Job.class), anyString())).thenReturn(jobs);

        List<Job> result = jobService.getAllOpenJobs();

        verify(mongoTemplate, times(1)).find(any(Query.class), eq(Job.class), anyString());
        assertEquals(2, result.size());
        assertEquals(jobs, result);
    }

    @Test
    public void testMarkJobAsOpen() {
        String jobId = "job123";

        jobService.markJobAsOpen(jobId);

        verify(mongoTemplate, times(1)).findAndModify(any(Query.class), any(Update.class), eq(Job.class));
    }

    @Test
    public void testMarkJobAsClosed() {
        String jobId = "job123";

        jobService.markJobAsClosed(jobId);

        verify(mongoTemplate, times(1)).findAndModify(any(Query.class), any(Update.class), eq(Job.class));
    }

    @Test
    public void testSelectCandidateForJob() {
        String jobId = "job123";
        String candidateId = "candidate123";

        jobService.selectCandidateForJob(jobId, candidateId);

        verify(mongoTemplate, times(1)).findAndModify(any(Query.class), any(Update.class), eq(Job.class));
    }

    @Test
    public void testGetJobsCandidateAcceptedTo() {
        String candidateId = "candidate123";
        Job job1 = new Job();
        job1.setSelectedCandidateId(candidateId);
        Job job2 = new Job();
        job2.setSelectedCandidateId(candidateId);
        List<Job> jobs = Arrays.asList(job1, job2);

        when(mongoTemplate.find(any(Query.class), eq(Job.class), anyString())).thenReturn(jobs);

        List<Job> result = jobService.getJobsCandidateAcceptedTo(candidateId);

        verify(mongoTemplate, times(1)).find(any(Query.class), eq(Job.class), anyString());
        assertEquals(2, result.size());
        assertEquals(jobs, result);
    }
}
