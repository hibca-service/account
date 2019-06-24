package bit5.team2.account.service.impl;

import bit5.team2.account.repo.ProfileRepo;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.ProfileService;
import bit5.team2.library.base.BaseService;
import bit5.team2.library.entity.Profile;
import bit5.team2.library.entity.User;
import bit5.team2.library.input.account.InChangeProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl extends BaseService implements ProfileService {
	@Autowired
	UserRepo userRepo;

	@Autowired
	ProfileRepo profileRepo;

	@Override
	public boolean changeProfile(String username, InChangeProfile input) {
		Optional<User> userOptional = userRepo.findUserByUsernameAndFirebaseTokenIsNotNullAndFirebaseUUIDIsNotNull(username);
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

	@Override
	public Profile getProfile(String username) {
		Optional<Profile> profileOptional = profileRepo.findProfileByUsername(username);
		return profileOptional.orElse(null);
    }
}
