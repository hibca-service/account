package bit5.team2.account.service;

import bit5.team2.library.output.account.OutLoginWeb;

public interface AdminLoginService {
	OutLoginWeb login(String username, String password);

	OutLoginWeb reLogin(String userId, String refreshToken);
}
