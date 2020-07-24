package healthcheck.application.repository;

import healthcheck.application.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("SELECT activity FROM Activity activity WHERE activity.userid = :userid")
    List<Activity> getUserActivities(@Param("userid") Long userid);
}
