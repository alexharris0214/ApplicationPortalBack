package com.adp.application_portal_job.repository;

import com.adp.application_portal_job.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends MongoRepository<Job, String> {
    Optional<Job> findJobById(String id);
}
