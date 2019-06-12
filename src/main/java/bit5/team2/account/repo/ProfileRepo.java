package bit5.team2.account.repo;

import java.util.ArrayList;

import bit5.team2.account.model.entity.Profile;

public class ProfileRepo {
	private ArrayList<Profile> profiles;
	
	public ProfileRepo() {
	    this.profiles = new ArrayList<>();
	}
	
	public ArrayList<Profile> getProfiles() {
	    return profiles;
	}
	
	public void save(Profile profile) {
	    this.profiles.add(profile);
	}
	
	public int nextId() {
	    if (profiles.isEmpty()) return 1;
	    else {
	        int id = 0;
	        for(Profile profile : this.profiles) {
	            if (Integer.valueOf(profile.getId()) > id) {
	                id = Integer.valueOf(profile.getId()) + 1;
	            }
	        }
	        return id;
	    }
	}
	
	public Profile findByUsername(String username) {
    	for (Profile profile : profiles) {
    		if (profile.getUsername().equals(username)) {
    			return profile;
    		}
    	}
    	return null;
    }

}
