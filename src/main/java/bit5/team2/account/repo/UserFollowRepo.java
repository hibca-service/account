package bit5.team2.account.repo;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import bit5.team2.account.model.entity.UserFollow;

@Repository
public class UserFollowRepo {
	private ArrayList<UserFollow> profiles;
	
	public UserFollowRepo() {
	    this.profiles = new ArrayList<>();
	}
	
	public ArrayList<UserFollow> getProfiles() {
	    return profiles;
	}
	
	public void save(UserFollow profile) {
	    this.profiles.add(profile);
	}
	
	public int nextId() {
	    if (profiles.isEmpty()) return 1;
	    else {
	        int id = 0;
	        for(UserFollow profile : this.profiles) {
	            if (Integer.valueOf(profile.getId()) > id) {
	                id = Integer.valueOf(profile.getId()) + 1;
	            }
	        }
	        return id;
	    }
	}
	
	public UserFollow findByUsername(String username) {
    	for (UserFollow profile : profiles) {
    		if (profile.getUsername().equals(username)) {
    			return profile;
    		}
    	}
    	return null;
    }

}
