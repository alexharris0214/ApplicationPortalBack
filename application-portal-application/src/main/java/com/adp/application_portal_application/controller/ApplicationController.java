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

    @GetMapping("/get-for-job/{jobId}")
    public ResponseEntity<List<Application>> getApplicationsForJobs(@PathVariable String jobId){
        List<Application> applications = applicationService.getAllApplicationsForJob(jobId);
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/get-for-candidate/{candidateId}")
    public ResponseEntity<List<Application>> getApplicationsForCandidate(@PathVariable String candidateId){
        List<Application> applications = applicationService.getAllApplicationsForCandidate(candidateId);
        return ResponseEntity.ok(applications);
    }

}
