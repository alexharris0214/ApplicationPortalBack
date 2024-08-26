package com.adp.application_portal_application.service;

import com.adp.application_portal_application.models.Application;
import com.adp.application_portal_application.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class ApplicationServiceImpl implements ApplicationService{
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Application createApplication(Application newApplication) {
        applicationRepository.save(newApplication);
        return newApplication;
    }

    @Override
    public Application updateApplication(String applicationId, Application updatedApplication) {
        Query findApplicationQuery = new Query(Criteria.where("_id").is(applicationId));
        Update update = new Update();
        update.set("userId", updatedApplication.getUserId());
        update.set("jobId", updatedApplication.getJobId());
        update.set("dateApplied", updatedApplication.getDateApplied());
        update.set("coverLetter", updatedApplication.getCoverLetter());
        update.set("resume", updatedApplication.getResume());
        update.set("open", updatedApplication.isOpen());

        return mongoTemplate.findAndModify(findApplicationQuery, update, Application.class);
    }

    @Override
    public void deleteApplication(String applicationId) {
        applicationRepository.deleteById(applicationId);
    }

    @Override
    public List<Application> getAllApplicationsForJob(String jobId) {
        Query findApplicationsForJobQuery = new Query(Criteria.where("jobId").is(jobId));
        return mongoTemplate.find(findApplicationsForJobQuery, Application.class);
    }
}
