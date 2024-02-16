package com.airventure.airventureback.activity.application;
import com.airventure.airventureback.activity.domain.entity.Activity;
import com.airventure.airventureback.activity.domain.service.ActivityService;
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

    @PostMapping("/activities/")
    Activity createActivity (@RequestBody Activity newActivity) {
        return activityService.createActivity(newActivity);
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
