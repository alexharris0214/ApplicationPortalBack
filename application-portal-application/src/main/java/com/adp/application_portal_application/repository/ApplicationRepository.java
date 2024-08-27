package com.adp.application_portal_application.repository;

import com.adp.application_portal_application.models.Application;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationRepository extends MongoRepository<Application, String> {
}
