package com.adp.application_portal_application.controller;

import com.adp.application_portal_application.models.Application;
import com.adp.application_portal_application.service.ApplicationService;
import com.adp.application_portal_application.service.ApplicationServiceImpl;
import com.adp.application_portal_application.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/applications")
public class ApplicationController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/create-application")
    public ResponseEntity<Application> createApplication(@RequestBody Application application){
        Application createdApplication = applicationService.createApplication(application);
        return ResponseEntity.ok(createdApplication);
    }

    @PatchMapping("/update-application")
    public ResponseEntity<Application> updateApplication(@RequestBody String applicationId, Application application){
        Application updateApplication = applicationService.updateApplication(applicationId, application);
        return ResponseEntity.ok(updateApplication);
    }

    @DeleteMapping("/delete-application")
    public ResponseEntity<String> deleteApplication(@RequestBody String applicationId){
        applicationService.deleteApplication(applicationId);
        return ResponseEntity.ok("Deleted Application with ID: " + applicationId);
    }

    @GetMapping("/get-for-jobs")
    public ResponseEntity<List<Application>> getApplicationsForJobs(@RequestBody String jobId){
        List<Application> applications = applicationService.getAllApplicationsForJob(jobId);
        return ResponseEntity.ok(applications);
    }
}
