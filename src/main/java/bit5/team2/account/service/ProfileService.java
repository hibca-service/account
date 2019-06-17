package bit5.team2.account.service;

import bit5.team2.account.model.input.InChangeProfile;
import bit5.team2.account.model.output.OutGetProfile;

public interface ProfileService {
	boolean changeProfile(InChangeProfile input);
	OutGetProfile getProfile(String username);
}
