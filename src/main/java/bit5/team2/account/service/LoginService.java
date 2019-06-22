package bit5.team2.account.service;

import bit5.team2.library.output.account.OutLoginMobile;

public interface LoginService {
	Object login(String username, String password);

    OutLoginMobile generateToken(String userId);

    OutLoginMobile generateToken(String userId, String refreshToken);
}
