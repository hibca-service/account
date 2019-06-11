package bit5.team2.account.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit5.team2.account.model.RegisterInput;
import bit5.team2.account.model.User;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	UserRepo userRepo;
	
	public void register(RegisterInput input) {
		String convertedPassword = new String();
		
		//encrypt password
		try {
        	MessageDigest digest = MessageDigest.getInstance("SHA-256");
        	byte[] encodedhash = digest.digest(input.getPassword().getBytes(StandardCharsets.UTF_8));
        	convertedPassword = bytesToHex(encodedhash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		//put data into db
		User user = new User();
		user.setId(String.valueOf(userRepo.nextId()));
		user.setName(input.getName());
		user.setUsername(input.getUsername());
		user.setEmail(input.getEmail());
		user.setPassword(convertedPassword);
		user.setPhoneNumber(input.getPhoneNumber());
		user.setDateOfBirth(input.getDateOfBirth());
		user.setPurpose(input.getPurpose());
		user.setOa(input.isOa());

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
