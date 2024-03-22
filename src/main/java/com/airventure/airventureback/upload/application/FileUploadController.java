package com.airventure.airventureback.upload.application;

import com.airventure.airventureback.upload.domain.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class FileUploadController {

    private final IStorageService storageService;

    @Autowired
    public FileUploadController(IStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<MultipartFile> handleFileUpload(@RequestParam("file") MultipartFile file) {
        System.out.println(file);
        storageService.store(file);
        return ResponseEntity.status(200).build();
    }
}