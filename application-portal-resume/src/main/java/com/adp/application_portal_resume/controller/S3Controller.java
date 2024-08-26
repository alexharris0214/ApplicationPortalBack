package com.adp.application_portal_resume.controller;

import com.adp.application_portal_resume.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

@RestController
@RequestMapping("api/resume")
@RequiredArgsConstructor
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @GetMapping("/upload-presigned-url")
    public String getUploadPresignedUrl() {
        URL url = s3Service.generateUploadURL();
        return url.toString();
    }
}