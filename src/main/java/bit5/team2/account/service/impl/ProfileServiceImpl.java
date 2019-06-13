package bit5.team2.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit5.team2.account.model.entity.UserFollow;
import bit5.team2.account.model.entity.User;
import bit5.team2.account.model.input.InChangeProfile;
import bit5.team2.account.model.output.OutGetProfile;
import bit5.team2.account.repo.UserFollowRepo;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UserFollowRepo userFollowRepo;
	
	public int changeProfile(InChangeProfile input) {
		User user = userRepo.findByUsername(input.getUsername());
		UserFollow userFollow = userFollowRepo.findByUsername(input.getUsername());
		
		if ( (input.getEmail() != null) && (input.getEmail() != "") ) {
			if (userRepo.findByEmail(input.getEmail()) == null) {
				user.setEmail(input.getEmail());
			} else {
				return 1;
			}
		}
		
		if ( (input.getPhoneNumber() != null) && (input.getPhoneNumber() != "") ) {
			if (userRepo.findByPhoneNumber(input.getPhoneNumber()) == null) {
				user.setPhoneNumber(input.getPhoneNumber());
			} else {
				return 1;
			}
		}
		
		if ( (input.getDateOfBirth() != null) ) {
			user.setDateOfBirth(input.getDateOfBirth());
		}
		
		if ((input.getName() != null) && (input.getName() != "")) {
			user.setName(input.getName());
		}
		
		if ((input.getPassword() != null) && (input.getPassword() != "")) {
			user.setPassword(input.getPassword());
		}
		
		if ((input.getPurpose() != null) && (input.getPurpose() != "")) {
			user.setPurpose(input.getPurpose());
		}
		
		if (input.isOa() == true) {
			user.setOa(true);
		} else {
			user.setOa(false);
		}
		
		if ((input.getStatus() != null) && (input.getStatus() != "")) {
			userFollow.setStatus(input.getStatus());
		}
		
		return 0;
	}
	
	public OutGetProfile getProfile(String username) {
		UserFollow userFollow = userFollowRepo.findByUsername(username);
		OutGetProfile output = new OutGetProfile();
		
		if ( userFollow != null ) {
			output.setFollowers(userFollow.getFollowers());
			output.setFollowing(userFollow.getFollowing());
			output.setName(userFollow.getName());
			output.setStatus(userFollow.getStatus());
			output.setUsername(userFollow.getUsername());
			
			return output;
		} 
		
		return null;
    }
}
