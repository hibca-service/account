package bit5.team2.account.service.impl;

import bit5.team2.account.model.Constant;
import bit5.team2.account.model.input.InChangePassword;
import bit5.team2.account.model.input.InChangeProfile;
import bit5.team2.account.repo.ProfileRepo;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.ProfileService;
import bit5.team2.library.base.BaseService;
import bit5.team2.library.entity.User;
import bit5.team2.library.view.ProfileView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
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
			if (input.getPathProfilePicture() != null && !input.getPathProfilePicture().equals("")) {
				user.setPathProfilePicture(input.getPathProfilePicture());
			}
			if (user.getPathProfilePicture() == null) {
				user.setPathProfilePicture(user.getOa().equals("1") ? Constant.DEFAULT_PROFILE_PICTURE_OA : Constant.DEFAULT_PROFILE_PICTURE_USER);
			}
			if (input.getName() != null && !input.getName().equals("")) {
				user.setName(input.getName());
			}
			if (input.getStatus() != null && !input.getStatus().equals("")) {
				user.setUserStatus(input.getStatus());
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
	public ProfileView getProfile(String username) {
		Optional<ProfileView> profileOptional = profileRepo.findProfileViewByUsername(username);
		return profileOptional.orElse(null);
    }

	@Override
	public boolean changePassword(String username, InChangePassword input) {
		Optional<User> userOptional = userRepo.findUserByUsernameAndPassword(username,this.hash(input.getOldPassword()));
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setPassword(this.hash(input.getNewPassword()));
			userRepo.save(user);
			return true;
		} else {
			return false;
		}
	}
}
