package com.adp.application_portal_application.service;

import com.adp.application_portal_application.models.Application;
import com.adp.application_portal_application.repository.ApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ApplicationServiceImplTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private ApplicationServiceImpl applicationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateApplication() {
        Application newApplication = new Application();
        newApplication.setUserId("user123");
        newApplication.setJobId("job123");

        when(applicationRepository.save(any(Application.class))).thenReturn(newApplication);

        Application createdApplication = applicationService.createApplication(newApplication);

        verify(applicationRepository, times(1)).save(newApplication);
        assertEquals("user123", createdApplication.getUserId());
        assertEquals("job123", createdApplication.getJobId());
    }

    @Test
    public void testUpdateApplication() {
        String applicationId = "app123";
        Date date = new Date();
        Application updatedApplication = new Application();
        updatedApplication.setUserId("user456");
        updatedApplication.setJobId("job456");
        updatedApplication.setDateApplied(date);
        updatedApplication.setCoverLetter("New Cover Letter");
        updatedApplication.setResume("New Resume");
        updatedApplication.setOpen(true);

        when(mongoTemplate.findAndModify(any(Query.class), any(Update.class), eq(Application.class))).thenReturn(updatedApplication);

        Application result = applicationService.updateApplication(applicationId, updatedApplication);

        verify(mongoTemplate, times(1)).findAndModify(any(Query.class), any(Update.class), eq(Application.class));
        assertEquals("user456", result.getUserId());
        assertEquals("job456", result.getJobId());
        assertEquals(date, result.getDateApplied());
        assertEquals("New Cover Letter", result.getCoverLetter());
        assertEquals("New Resume", result.getResume());
        assertEquals(true, result.isOpen());
    }

    @Test
    public void testDeleteApplication() {
        String applicationId = "app123";

        doNothing().when(applicationRepository).deleteById(applicationId);

        applicationService.deleteApplication(applicationId);

        verify(applicationRepository, times(1)).deleteById(applicationId);
    }

    @Test
    public void testGetAllApplicationsForJob() {
        String jobId = "job123";
        List<Application> applications = new ArrayList<>();
        Application app1 = new Application();
        app1.setJobId(jobId);
        applications.add(app1);

        when(mongoTemplate.find(any(Query.class), eq(Application.class))).thenReturn(applications);

        List<Application> result = applicationService.getAllApplicationsForJob(jobId);

        verify(mongoTemplate, times(1)).find(any(Query.class), eq(Application.class));
        assertEquals(1, result.size());
        assertEquals(jobId, result.get(0).getJobId());
    }
}
