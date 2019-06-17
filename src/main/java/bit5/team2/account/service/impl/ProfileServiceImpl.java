package bit5.team2.account.service.impl;

import bit5.team2.account.model.FollowObject;
import bit5.team2.account.model.input.InChangeProfile;
import bit5.team2.account.model.output.OutGetProfile;
import bit5.team2.account.repo.UserFollowRepo;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.ProfileService;
import bit5.team2.library.base.BaseService;
import bit5.team2.library.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ProfileServiceImpl extends BaseService implements ProfileService {
	@Autowired
	UserRepo userRepo;

	@Autowired
	UserFollowRepo userFollowRepo;
	
	public boolean changeProfile(InChangeProfile input) {
		Optional<User> userOptional = userRepo.findUserByUsernameAndFirebaseTokenIsNotNullAndFirebaseUUIDIsNotNull(input.getUsername());
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			if (input.getPassword() != null && !input.getPassword().equals("")) {
				user.setPassword(this.hash(input.getPassword()));
			}
			if (input.getName() != null && !input.getName().equals("")) {
				user.setName(input.getName());
			}
			if (input.getDateOfBirth() != null && !input.getDateOfBirth().equals("")) {
				user.setDateOfBirth(input.getDateOfBirth());
			}
			if (input.getCityOfBirth() != null && !input.getCityOfBirth().equals("")) {
				user.setCityOfBirth(input.getCityOfBirth());
			}
			if (input.getPurpose() != null && !input.getPurpose().equals("")) {
				user.setPurpose(input.getPurpose());
			}
			userRepo.save(user);

			return true;
		} else {
			return false;
		}
	}
	
	public OutGetProfile getProfile(String username) {
		Optional<User> userOptional = userRepo.findUserByUsernameAndFirebaseTokenIsNotNullAndFirebaseUUIDIsNotNull(username);
		if (userOptional.isPresent()) {
			OutGetProfile outGetProfile = new OutGetProfile().init(userOptional.get());
			FollowObject followObject = userFollowRepo.findByUsername(username);
			outGetProfile.setFollower(followObject.getFollower());
			outGetProfile.setFollowing(followObject.getFollowing());
			return outGetProfile;
		} else {
			return null;
		}
    }
}
