package com.adp.application_portal_application.repository;

import com.adp.application_portal_application.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
