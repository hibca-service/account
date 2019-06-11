package bit5.team2.account.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit5.team2.account.model.input.Register1;
import bit5.team2.account.model.input.Register2;
import bit5.team2.account.model.input.Register3;
import bit5.team2.account.model.entity.User;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {
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
			if (!(checkEmail.isFinished() == true)) {
				return user.getId();
			}
		}
		return "failed";
    }
	
	public String registerStep2(Register2 input) {
		String convertedPassword = new String();
		User user = userRepo.findById(input.getId());
		User checkUsername = userRepo.findByUsername(input.getUsername());
		
		//check username
		if ( checkUsername == null ) {
			//put data into db
			try {
	        	MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        	byte[] encodedhash = digest.digest(input.getPassword().getBytes(StandardCharsets.UTF_8));
	        	convertedPassword = bytesToHex(encodedhash);
			} catch (NoSuchAlgorithmException e) {
				
				e.printStackTrace();
			}
			
	        user.setUsername(input.getUsername());
	        user.setPassword(convertedPassword);
			userRepo.save(user);
			return user.getId();
		} else {
			if ( !(user.isFinished() == true)) {
				return user.getId();
			}
		}
		return "failed";
    }
	
	public void registerStep3(Register3 input) {
		User user = userRepo.findById(input.getId());
		
		//put data into db
        user.setName(input.getName());
        user.setDateOfBirth(input.getDateOfBirth());
        user.setPurpose(input.getPurpose());
		
		userRepo.save(user);
	}
	
	private static String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
}
