package bit5.team2.account.service;

import bit5.team2.account.model.output.Token;

public interface LoginService {
	Token login(String username, String password);
}
