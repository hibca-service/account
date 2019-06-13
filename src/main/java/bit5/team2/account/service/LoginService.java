package bit5.team2.account.service;

import bit5.team2.account.model.output.OutLogin;
import bit5.team2.account.model.output.OutLoginAdmin;

public interface LoginService {
	OutLogin login(String username, String password);
	public OutLoginAdmin loginAdmin(String username, String password);
}
