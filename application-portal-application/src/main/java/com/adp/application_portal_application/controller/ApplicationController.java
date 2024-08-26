package com.adp.application_portal_application.controller;

import com.adp.application_portal_application.models.Application;
import com.adp.application_portal_application.models.requests.DeleteApplicationRequest;
import com.adp.application_portal_application.models.requests.GetApplicationsForJobRequest;
import com.adp.application_portal_application.service.ApplicationService;
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
    public ResponseEntity<String> deleteApplication(@RequestBody DeleteApplicationRequest request){
        applicationService.deleteApplication(request.getApplicationId());
        return ResponseEntity.ok("Deleted Application with ID: " + request.getApplicationId());
    }

    @GetMapping("/get-for-jobs")
    public ResponseEntity<List<Application>> getApplicationsForJobs(@RequestBody GetApplicationsForJobRequest request){
        List<Application> applications = applicationService.getAllApplicationsForJob(request.getJobId());
        return ResponseEntity.ok(applications);
    }
}
