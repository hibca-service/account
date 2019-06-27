package bit5.team2.account.service.impl;

import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.VerificationService;
import bit5.team2.library.entity.User;
import bit5.team2.library.input.account.InVerificationPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class VerificationServiceImpl implements VerificationService {
	@Autowired
	UserRepo userRepo;

	@Override
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
//		User user = profileRepo.findById(id);
//		if (user != null) {
//			if (user.isEmailVerified() == false) {
//				user.setEmailVerified(true);
//				return 0;
//			}
//		}
//		return 1;
//	}
}
