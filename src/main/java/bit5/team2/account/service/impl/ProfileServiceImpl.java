package bit5.team2.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit5.team2.account.model.entity.User;
import bit5.team2.account.model.input.ChangeProfileInput;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	UserRepo userRepo;
	
	public int changeProfile(ChangeProfileInput input) {
		User user = userRepo.findByUsername(input.getUsername());
		
		if (!(input.getEmail().equals(null))) {
			User checkEmail = userRepo.findByEmail(input.getEmail());
			if (checkEmail == null)
				user.setEmail(input.getEmail());
			else
				return 1;
		}
		
		if (!(input.getPhoneNumber().equals(null))) {
			User checkPhoneNumber = userRepo.findByPhoneNumber(input.getPhoneNumber());
			if (checkPhoneNumber == null)
				user.setPhoneNumber(input.getPhoneNumber());
			else
				return 1;
		}
		
		if (!(input.getDateOfBirth().equals(null))) {
			user.setDateOfBirth(input.getDateOfBirth());
		}
		
		if (!(input.getName().equals(null))) {
			user.setName(input.getName());
		}
		
		if (!(input.getPassword().equals(null))) {
			user.setPassword(input.getPassword());
		}
		
		if (!(input.getPurpose().equals(null))) {
			user.setPurpose(input.getPurpose());
		}
		
		if (input.isOa() == true) {
			user.setOa(true);
		} else {
			user.setOa(false);
		}
		return 0;
	}
}
