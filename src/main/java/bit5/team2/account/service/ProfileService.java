package bit5.team2.account.service;

import bit5.team2.library.input.account.InChangeProfile;
import bit5.team2.library.output.account.OutGetProfile;

public interface ProfileService {
	boolean changeProfile(String username, InChangeProfile input);
	OutGetProfile getProfile(String username);
}
