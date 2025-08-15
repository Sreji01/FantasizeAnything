package com.fantasize_anything.app;

import com.fantasize_anything.app.domain.Activity;
import com.fantasize_anything.app.dto.ActivityInput;
import com.fantasize_anything.app.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    public ResponseEntity<Activity> createActivity(@RequestParam ActivityInput request){
        Activity activity = activityService.createActivity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(activity);
    }
}
