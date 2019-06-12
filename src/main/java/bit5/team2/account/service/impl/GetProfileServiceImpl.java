package bit5.team2.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import bit5.team2.account.model.entity.Profile;
import bit5.team2.account.model.output.OutGetProfile;
import bit5.team2.account.repo.ProfileRepo;

@Service
public class GetProfileServiceImpl {
	@Autowired
	ProfileRepo profileRepo;
	
	public OutGetProfile getProfile(String username) {
		Profile profile = profileRepo.findByUsername(username);
		OutGetProfile output = new OutGetProfile();
		
		if ( profile != null ) {
			output.setFollowers(profile.getFollowers());
			output.setFollowing(profile.getFollowing());
			output.setName(profile.getName());
			output.setStatus(profile.getStatus());
			output.setUsername(profile.getUsername());
			
			return output;
		} 
		
		return null;
    }
}
