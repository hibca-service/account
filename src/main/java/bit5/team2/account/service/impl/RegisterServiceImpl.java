package bit5.team2.account.service.impl;

import bit5.team2.account.lib.BaseService;

import bit5.team2.account.model.entity.User;
import bit5.team2.account.model.input.InRegister;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl extends BaseService implements RegisterService {
	@Autowired
	UserRepo userRepo;
	
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
	 * return 9 = phone number already verified;
	 * return 10 = email already verified;
	 * */
	 
	public int register (InRegister input) {
		User user = new User();
		User checkEmail = userRepo.findByEmail(input.getEmail());
		User checkPhoneNumber = userRepo.findByPhoneNumber(input.getPhoneNumber());
		User checkUsername = userRepo.findByUsername(input.getUsername());
		boolean isEmailVerified;
		boolean isPhoneVerified;
		String hashedPassword = this.hash(input.getPassword());
		
		if (hashedPassword == null) {
			return 1;
		} else if ( (checkEmail != null) && (checkPhoneNumber != null) && (checkUsername != null) ) {
			return 2;
		} else if ( (checkEmail != null) && (checkPhoneNumber != null) ) {
			return 3;
		} else if ( (checkEmail != null) && (checkUsername != null) ) {
			return 4;
		} else if ( (checkPhoneNumber != null) && (checkUsername != null) ) {
			return 5;
		} else if ( (checkEmail != null) ) {
			return 6;
		} else if ( (checkPhoneNumber != null) ) {
			return 7;
		} else if ( (checkUsername != null) ) {
			return 8;
		}
		
		isEmailVerified = checkEmail.isEmailVerified();
		isPhoneVerified = checkPhoneNumber.isPhoneVerified();
		
		if (isPhoneVerified == true) {
			return 9;
		} else if (isEmailVerified == true) {
			return 10;
		}
		
		user.setId(String.valueOf(userRepo.nextId()));
		user.setEmail(input.getEmail());
		user.setPhoneNumber(input.getPhoneNumber());
		user.setOa(input.isOa());
		user.setUsername(input.getUsername());
        user.setPassword(hashedPassword);
		
        userRepo.save(user);
		
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