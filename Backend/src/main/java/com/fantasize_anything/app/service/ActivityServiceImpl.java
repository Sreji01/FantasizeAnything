package com.fantasize_anything.app.service;

import com.fantasize_anything.app.domain.Activity;
import com.fantasize_anything.app.domain.ScoringRule;
import com.fantasize_anything.app.domain.User;
import com.fantasize_anything.app.dto.ActivityInput;
import com.fantasize_anything.app.exception.UserNotFoundException;
import com.fantasize_anything.app.repository.ActivityRepository;
import com.fantasize_anything.app.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivityServiceImpl implements ActivityService{

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository, UserRepository userRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Activity createActivity(ActivityInput request) {
        Activity activity = new Activity();
        activity.setName(request.getName());
        activity.setDescription(request.getDescription());
        activity.setBudget(request.getBudget());
        activity.setNumberOfPlayers(request.getNumberOfPlayers());

        User creator = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));

        activity.setCreator(creator);

        request.getScoringRules().forEach(dto -> {
            ScoringRule scoringRule = new ScoringRule();
            scoringRule.setDescription(dto.getDescription());
            scoringRule.setNumberOfPoints(dto.getNumberOfPoints());
            activity.addScoringRule(scoringRule);
        });

        return activityRepository.save(activity);
    }
}
