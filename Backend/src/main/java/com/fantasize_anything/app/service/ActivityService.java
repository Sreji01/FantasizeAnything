package com.fantasize_anything.app.service;

import com.fantasize_anything.app.domain.Activity;
import com.fantasize_anything.app.dto.ActivityInput;

public interface ActivityService {

    public Activity createActivity(ActivityInput request);
}
