package bit5.team2.account.service;

import bit5.team2.account.model.output.Token;

public interface LoginService {
	public Token login(String username, String password);
	public void set();
}
