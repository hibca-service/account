package bit5.team2.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit5.team2.account.model.entity.User;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.VerificationService;

@Service
public class VerificationServiceImpl implements VerificationService {
	@Autowired
	UserRepo userRepo;
	
	public int viaEmail(String id) {
		User user = userRepo.findById(id);
		if (user != null) {
			if (user.isEmailVerified() == false) {
				user.setEmailVerified(true);
				return 0;
			}
		}
		return 1;
	}
	
	public int viaPhone(String id) {
		User user = userRepo.findById(id);
		if (user != null) {
			if (user.isPhoneVerified() == false) {
				user.setPhoneVerified(true);
				return 0;
			}
		}
		return 1;
	}
}
