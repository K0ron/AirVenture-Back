package com.airventure.airventureback.activity.application;
import com.airventure.airventureback.activity.domain.entity.Activity;
import com.airventure.airventureback.activity.domain.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;

    }

    @GetMapping("/activities")
    List<Activity> getAll(){
        return activityService.getAllActivities();
    }


    @GetMapping("/activities/{id}")
    Activity getOne (@PathVariable Long id) {
        return activityService.getOneActivity(id);
    }

    @PostMapping("/activities")
    public ResponseEntity<?> createActivity (@RequestBody Activity newActivity) throws Exception {
        try {
            return ResponseEntity.status(201).body(activityService.createActivity(newActivity));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("/activities/{id}")
    Activity modify(@RequestBody Activity newActivity, @PathVariable Long id) {
        return activityService.updateActivity(newActivity, id);
    }

    @DeleteMapping ("/activities/{id}")
    void delete(@PathVariable Long id) {
        activityService.deleteActivity(id);
    }
}
