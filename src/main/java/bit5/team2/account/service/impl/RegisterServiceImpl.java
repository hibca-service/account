package bit5.team2.account.service.impl;

import bit5.team2.account.lib.BaseService;
import bit5.team2.account.model.entity.Admin;
import bit5.team2.account.model.entity.User;
import bit5.team2.account.model.entity.UserFollow;
import bit5.team2.account.model.input.InRegister;
import bit5.team2.account.repo.AdminRepo;
import bit5.team2.account.repo.UserFollowRepo;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl extends BaseService implements RegisterService {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AdminRepo adminRepo;
	
	@Autowired
	UserFollowRepo userFollowRepo;
	
	/*
	 * List output if else case :
	 * return 0 = success;
	 * return 1 = failed hashed password;
	 * return 2 = email, phone number, username already taken;
	 * return 3 = email, phone number already taken;
	 * return 4 = email, username already taken;
	 * return 5 = phone number, username already taken;
	 * return 6 = email already taken; 
	 * return 7 = phone number already taken;
	 * return 8 = username already taken;
	 * */
	 
	public int register (InRegister input) {
		User user = new User();
		User registeredUser = new User();
		int flag = 0;
		User checkEmail = userRepo.findByEmail(input.getEmail());
		User checkPhoneNumber = userRepo.findByPhoneNumber(input.getPhoneNumber());
		User checkUsername = userRepo.findByUsername(input.getUsername());
		Admin checkAdminUsername = adminRepo.findByUsername(input.getUsername());
		String hashedPassword = this.hash(input.getPassword());
		
		if (hashedPassword == null) {
			return 1;
		} else if ( (checkEmail != null) && (checkPhoneNumber != null) && (checkUsername != null && checkAdminUsername != null) ) {
			return 2;
		} else if ( (checkEmail != null) && (checkPhoneNumber != null) ) {
			boolean isEmailVerified = checkEmail.isEmailVerified();
			boolean isPhoneVerified = checkPhoneNumber.isPhoneVerified();
			
			if ( (isEmailVerified == true) && (isPhoneVerified == true) ) {
				return 3; // email & PN registered and validated
			}
			
			flag = 1;
			registeredUser = checkEmail;
		} else if ( (checkEmail != null) && (checkUsername != null && checkAdminUsername != null) ) {
			return 4;
		} else if ( (checkPhoneNumber != null) && (checkUsername != null && checkAdminUsername != null) ) {
			return 5;
		} else if ( (checkEmail != null) ) {
			boolean isEmailVerified = checkEmail.isEmailVerified();
			
			if (isEmailVerified == true) {
				return 6; // email registered and validated
			}
			
			flag = 1;
			registeredUser = checkEmail;
			
		} else if ( (checkPhoneNumber != null) ) {
			boolean isPhoneVerified = checkPhoneNumber.isPhoneVerified();
			
			if (isPhoneVerified == true) {
				return 7; // PN registered and validated
			}
			
			flag = 1;
			registeredUser = checkPhoneNumber;
		} else if ( (checkUsername != null && checkAdminUsername != null) ) {
			return 8;
		}
		
		if (flag == 0) {
			user.setId(String.valueOf(userRepo.nextId()));
			user.setEmail(input.getEmail());
			user.setPhoneNumber(input.getPhoneNumber());
			user.setOa(input.isOa());
			user.setUsername(input.getUsername());
			user.setEmailVerified(false);
			user.setPhoneVerified(false);
	        user.setPassword(hashedPassword);
	        
	        UserFollow userFollow = new UserFollow();
	        userFollow.setFollowers(0);
	        userFollow.setFollowing(0);
	        userFollow.setId(String.valueOf(userRepo.nextId()));
	        userFollow.setName(null);
	        userFollow.setUsername(input.getUsername());
			
	        userFollowRepo.save(userFollow);
			
	        userRepo.save(user);
		} else {
			registeredUser.setEmail(input.getEmail());
			registeredUser.setPhoneNumber(input.getPhoneNumber());
			registeredUser.setOa(input.isOa());
			registeredUser.setUsername(input.getUsername());
			registeredUser.setEmailVerified(false);
			registeredUser.setPhoneVerified(false);
			registeredUser.setPassword(hashedPassword);
			
			UserFollow userFollow = userFollowRepo.findByUsername(input.getUsername());
			userFollow.setFollowers(0);
	        userFollow.setFollowing(0);
	        userFollow.setName(null);
	        
	        userFollowRepo.save(userFollow);
	        userRepo.save(registeredUser);
		}
        
		return 0;
	}
}
//public String registerStep1(InRegister1 input) {
//User user = new User();
//User checkEmail = userRepo.findByEmail(input.getEmail());
//User checkPhoneNumber = userRepo.findByPhoneNumber(input.getPhoneNumber());
//
////check email and phone number data
//if ( checkEmail == null ) {
//	if ( checkPhoneNumber == null ) {
//		//put data into db
//		user.setId(String.valueOf(userRepo.nextId()));
//		user.setEmail(input.getEmail());
//		user.setPhoneNumber(input.getPhoneNumber());
//		user.setOa(input.isOa());
//		
//		userRepo.save(user);
//		
//		return user.getId();
//	}
//} else {
//	if (!(checkEmail.isFinished())) {
//		return user.getId();
//	}
//}
//return null;
//}
//
//public String registerStep2(InRegister2 input) {
//User user = userRepo.findById(input.getId());
//User checkUsername = userRepo.findByUsername(input.getUsername());
//
////check username
//if ( checkUsername == null ) {
//	//put data into db
//	String hashedPassword = this.hash(input.getPassword());
//	if (hashedPassword == null) {
//		return null;
//	}
//	
//    user.setUsername(input.getUsername());
//    user.setPassword(hashedPassword);
//	userRepo.save(user);
//	return user.getId();
//} else {
//	if ( !(user.isFinished())) {
//		return user.getId();
//	}
//}
//return null;
//}
//
//public void registerStep3(InRegister3 input) {
//User user = userRepo.findById(input.getId());
//
////put data into db
//user.setName(input.getName());
//user.setDateOfBirth(input.getDateOfBirth());
//user.setPurpose(input.getPurpose());
//
//userRepo.save(user);
//}

//User checkPhoneNumberIfEmailValid = userRepo.findByPhoneNumber(input.getPhoneNumber());
//if (checkPhoneNumberIfEmailValid != null) {
//	return 12; // if email valid and phone number is taken.
//}

