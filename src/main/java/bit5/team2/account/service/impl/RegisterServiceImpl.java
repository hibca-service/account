package bit5.team2.account.service.impl;

import bit5.team2.account.lib.BaseService;
import bit5.team2.account.model.entity.User;
import bit5.team2.account.model.input.Register1;
import bit5.team2.account.model.input.Register2;
import bit5.team2.account.model.input.Register3;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl extends BaseService implements RegisterService {
	@Autowired
	UserRepo userRepo;
	
	public String registerStep1(Register1 input) {
		User user = new User();
		User checkEmail = userRepo.findByEmail(input.getEmail());
		
		//check email and phone number data
		if ( checkEmail == null ) {
			//put data into db
			user.setId(String.valueOf(userRepo.nextId()));
			user.setEmail(input.getEmail());
			user.setPhoneNumber(input.getPhoneNumber());
			user.setOa(input.isOa());
			
			userRepo.save(user);
			
			return user.getId();
		} else {
			if (!(checkEmail.isFinished())) {
				return user.getId();
			}
		}
		return null;
    }
	
	public String registerStep2(Register2 input) {
		User user = userRepo.findById(input.getId());
		User checkUsername = userRepo.findByUsername(input.getUsername());
		
		//check username
		if ( checkUsername == null ) {
			//put data into db
			String hashedPassword = this.hash(input.getPassword());
			if (hashedPassword == null) {
				return null;
			}
			
	        user.setUsername(input.getUsername());
	        user.setPassword(hashedPassword);
			userRepo.save(user);
			return user.getId();
		} else {
			if ( !(user.isFinished())) {
				return user.getId();
			}
		}
		return null;
    }
	
	public void registerStep3(Register3 input) {
		User user = userRepo.findById(input.getId());
		
		//put data into db
        user.setName(input.getName());
        user.setDateOfBirth(input.getDateOfBirth());
        user.setPurpose(input.getPurpose());
		
		userRepo.save(user);
	}
}
