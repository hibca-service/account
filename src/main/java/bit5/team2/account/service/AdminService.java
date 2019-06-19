package bit5.team2.account.service;

import bit5.team2.account.model.input.InCreateCoAdmin;

public interface AdminService {
	public int createCoAdmin(InCreateCoAdmin input, String createdBy);
}
