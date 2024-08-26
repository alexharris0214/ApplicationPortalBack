package com.adp.application_portal_application.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
