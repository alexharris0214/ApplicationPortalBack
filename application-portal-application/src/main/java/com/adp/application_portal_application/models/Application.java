package com.adp.application_portal_application.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "applications")
public class Application {
    @Id
    private String id;
    private String userId;
    private String jobId;
    private Date dateApplied;
    private String coverLetter;
    private String resume;
    private boolean open;
}
