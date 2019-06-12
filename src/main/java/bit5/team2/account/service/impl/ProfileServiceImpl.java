package bit5.team2.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit5.team2.account.model.entity.User;
import bit5.team2.account.model.input.ChangeProfile;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	UserRepo userRepo;
	
	public int changeProfile(ChangeProfile input) {
		User user = userRepo.findByUsername(input.getUsername());
		
		if (input.getEmail() != null) {
			if (userRepo.findByEmail(input.getEmail()) == null) {
				user.setEmail(input.getEmail());
			} else {
				return 1;
			}
		}
		
		if (input.getPhoneNumber() != null) {
			if (userRepo.findByPhoneNumber(input.getPhoneNumber()) == null) {
				user.setPhoneNumber(input.getPhoneNumber());
			} else {
				return 1;
			}
		}
		
		if (input.getDateOfBirth() != null) {
			user.setDateOfBirth(input.getDateOfBirth());
		}
		
		if (input.getName() != null) {
			user.setName(input.getName());
		}
		
		if (input.getPassword() != null) {
			user.setPassword(input.getPassword());
		}
		
		if (input.getPurpose() != null) {
			user.setPurpose(input.getPurpose());
		}
		
		if (input.isOa()) {
			user.setOa(true);
		} else {
			user.setOa(false);
		}
		return 0;
	}
}
