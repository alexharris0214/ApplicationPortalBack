package com.adp.application_portal_application.service;

import com.adp.application_portal_application.models.Application;

import java.util.List;

public interface ApplicationService {
    Application createApplication(Application newApplication);
    Application updateApplication(String applicationId, Application updatedApplication);
    void deleteApplication(String applicationId);
    List<Application> getAllApplicationsForJob(String jobId);
}
