package bit5.team2.account.service;

import bit5.team2.account.model.input.InChangeProfile;
import bit5.team2.account.model.output.OutGetProfile;

public interface ProfileService {
	int changeProfile(InChangeProfile input);
	public OutGetProfile getProfile(String username);
}
