package bit5.team2.account.service;

import bit5.team2.account.model.output.OutLoginWeb;

public interface AdminLoginService {
	OutLoginWeb reLogin(String userId, String refreshToken);
}
