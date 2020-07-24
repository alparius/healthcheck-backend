package healthcheck.application.service;

import healthcheck.application.model.Activity;
import healthcheck.application.model.StatusEnum;
import healthcheck.application.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xps 9560
 */

@Service
@RequiredArgsConstructor
public class ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    public List<Activity> getUserActivities(Long userid){
        return activityRepository.getUserActivities(userid);
    }

    public Activity getActivityById(Long id){
        return activityRepository.findById(id).get();
    }

    public void deleteActivityById(Long activityId){
        this.activityRepository.deleteById(activityId);
    }

    public Activity addActivity(Activity activity){
        activity.setStatus(StatusEnum.ACCEPTED.toString());
        return this.activityRepository.save(activity);
    }

    public Activity updateActivity(Activity activity){
        return this.activityRepository.save(activity);
    }
}
