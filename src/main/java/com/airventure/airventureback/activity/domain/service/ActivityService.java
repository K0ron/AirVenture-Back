package com.airventure.airventureback.activity.domain.service;

import com.airventure.airventureback.activity.domain.entity.Activity;
import com.airventure.airventureback.activity.infrastructure.repository.ActivityRepository;
import com.airventure.airventureback.upload.domain.service.FileSystemStorageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    private final ActivityRepository repository;
    private final FileSystemStorageService fileSystemStorageService;

    public ActivityService(ActivityRepository repository, FileSystemStorageService fileSystemStorageService) {
        this.repository = repository;
        this.fileSystemStorageService = fileSystemStorageService;
    }

    public List<Activity> getAllActivities() {
        return repository.findAll();
    }

    public Activity getOneActivity(Long id) {
        return repository.findById(id)
                .orElseThrow(/*() -> new ArticleNotFoundException(id)*/)
                ;
    }

   public Activity createActivity(Activity newActivity) {
        newActivity.setPicture(this.fileSystemStorageService.cleanFileName(newActivity.getPicture()));
        return repository.save(newActivity);
    }

    public Activity updateActivity(Activity newActivity, Long id) {
        return repository.findById(id)
                .map( activity -> {
                    activity.setName(newActivity.getName());
                    activity.setDescription(newActivity.getDescription());
                    activity.setDuration(newActivity.getDuration());
                    activity.setLocation(newActivity.getLocation());
                    activity.setPrice(newActivity.getPrice());
                    activity.setPicture(newActivity.getPicture());

                    return repository.save(activity);
                })
                .orElseThrow(/*() -> new ArticleNotFoundException(id)*/);
    }

    public void deleteActivity(Long id) {
        repository.deleteById(id);
    }
}