package bit5.team2.account.service.impl;

import bit5.team2.account.model.input.InVerificationPhone;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.VerificationService;
import bit5.team2.library.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationServiceImpl implements VerificationService {
	@Autowired
	UserRepo userRepo;

	public boolean viaPhone(InVerificationPhone input) {
		Optional<User> userOptional = userRepo.findUserByUserIdAndFirebaseTokenIsNullAndFirebaseUUIDIsNull(input.getUserId());
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setFirebaseToken(input.getFirebaseToken());
			user.setFirebaseUUID(input.getFirebaseUUID());
			userRepo.save(user);
			return true;
		}
		return false;
	}
	
//	public int viaEmail(String id) {
//		User user = userRepo.findById(id);
//		if (user != null) {
//			if (user.isEmailVerified() == false) {
//				user.setEmailVerified(true);
//				return 0;
//			}
//		}
//		return 1;
//	}
}
