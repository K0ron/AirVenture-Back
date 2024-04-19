package com.airventure.airventureback.upload.application;

import com.airventure.airventureback.activity.domain.service.ActivityService;
import com.airventure.airventureback.upload.domain.service.IStorageService;
import com.airventure.airventureback.upload.infrastructure.exception.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class FileUploadController {

    private final IStorageService storageService;
    private final ActivityService activityService;

    @Autowired
    public FileUploadController(IStorageService storageService, ActivityService activityService) {
        this.storageService = storageService;
        this.activityService = activityService;
    }

    @PostMapping("/upload")
    public ResponseEntity<MultipartFile> handleFileUpload(@RequestParam("file") MultipartFile file) {
        storageService.store(file);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/files/{id}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable Long id) {

        String filename = activityService.getOneActivity(id).getPicture();
        Resource file = storageService.loadAsResource(filename);

        if (file == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentType(MediaType.IMAGE_PNG)
                .body(file);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}