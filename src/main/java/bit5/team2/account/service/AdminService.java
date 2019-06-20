package bit5.team2.account.service;

import java.util.List;

import bit5.team2.account.model.input.InCreateCoAdmin;
import bit5.team2.account.model.input.InUpdateCoAdmin;
import bit5.team2.account.model.output.OutReadCoAdmin;

public interface AdminService {
	public int createCoAdmin(InCreateCoAdmin input, String createdBy);
	public List<OutReadCoAdmin> readCoAdmin();
	public boolean updateCoAdmin(InUpdateCoAdmin input, String adminId);
}
