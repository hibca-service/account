package bit5.team2.account.service;

import bit5.team2.account.model.output.OutLogin;

public interface LoginService {
	OutLogin login(String username, String password);
}
