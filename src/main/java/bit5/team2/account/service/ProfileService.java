package bit5.team2.account.service;

import bit5.team2.library.entity.Profile;
import bit5.team2.library.input.account.InChangeProfile;

public interface ProfileService {
	boolean changeProfile(String username, InChangeProfile input);
	Profile getProfile(String username);
}
