package bit5.team2.account.service;

import bit5.team2.account.model.input.InChangePassword;
import bit5.team2.account.model.input.InChangeProfile;
import bit5.team2.library.view.ProfileView;

public interface ProfileService {
	boolean changeProfile(String username, InChangeProfile input);

	ProfileView getProfile(String username);

    boolean changePassword(String username, InChangePassword input);
}
