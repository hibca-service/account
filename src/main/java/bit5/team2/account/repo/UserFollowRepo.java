package bit5.team2.account.repo;

import bit5.team2.account.model.FollowObject;
import org.springframework.stereotype.Repository;

@Repository
public class UserFollowRepo {
    public FollowObject findByUsername(String username) {
        FollowObject followObject = new FollowObject();
        followObject.setFollower(0);
        followObject.setFollowing(0);
        return followObject;
    }
}
