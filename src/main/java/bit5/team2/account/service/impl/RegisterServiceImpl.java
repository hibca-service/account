package bit5.team2.account.service.impl;

import bit5.team2.account.model.RegisterValidator;
import bit5.team2.account.model.input.InRegister;
import bit5.team2.account.repo.*;
import bit5.team2.account.service.RegisterService;
import bit5.team2.library.base.BaseService;
import bit5.team2.library.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterServiceImpl extends BaseService implements RegisterService {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AdminRepo adminRepo;
	
	@Autowired
	UserFollowRepo userFollowRepo;

	private boolean _isOaValid(InRegister inRegister) {
		return inRegister.getPurpose() != null;
	}

	private boolean _isUserValid(InRegister inRegister) {
		return true;
	}

	private User _createOa(InRegister inRegister) {
		User user = new User();
		user.setOa("1");
		user.setUsername(inRegister.getUsername());
		user.setPassword(this.hash(inRegister.getPassword()));
		user.setPhoneNumber(inRegister.getPhoneNumber());
		user.setPurpose(inRegister.getPurpose());
		return user;
	}

	private User _createUser(InRegister inRegister) {
		User user = new User();
		user.setOa("0");
		user.setUsername(inRegister.getUsername());
		user.setName(inRegister.getUsername());
		user.setPassword(this.hash(inRegister.getPassword()));
		user.setPhoneNumber(inRegister.getPhoneNumber());
		return user;
	}

	public RegisterValidator register(InRegister input) {
		RegisterValidator registerValidator = new RegisterValidator();

		ArrayList<String> invalid = new ArrayList<>();

		List<User> userList = userRepo.findUserByUsernameOrPhoneNumber(input.getUsername(),input.getPhoneNumber());

		if (userList != null) {
			for (User user : userList) {
				if (user.getFirebaseToken() != null) {
					if (user.getUsername().equals(input.getUsername())) {
						invalid.add("username");
					}
					else if (user.getPhoneNumber().equals(input.getPhoneNumber())) {
						invalid.add("phone number");
					}
				}
			}
		}
		if (invalid.isEmpty()) {
			switch (input.getOa()) {
				case "1":
					if (_isOaValid(input)) {
						User user = this._createOa(input);
						this.userRepo.save(user);
						registerValidator.setUserId(user.getUserId());
					}
					break;
				case "0":
					if (_isUserValid(input)) {
						User user = this._createUser(input);
						this.userRepo.save(user);
						registerValidator.setUserId(user.getUserId());
					}
					break;
			}
		}
		registerValidator.setInvalid(invalid);
		return registerValidator;
	}
}
