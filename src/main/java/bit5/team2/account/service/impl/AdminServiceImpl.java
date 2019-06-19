package bit5.team2.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit5.team2.account.model.input.InCreateCoAdmin;
import bit5.team2.account.repo.AdminRepo;
import bit5.team2.account.service.AdminService;
import bit5.team2.library.base.BaseService;
import bit5.team2.library.entity.Admin;

@Service
public class AdminServiceImpl extends BaseService implements AdminService {
	@Autowired
	AdminRepo adminRepo;
	
	public int createCoAdmin(InCreateCoAdmin input, String createdBy) {
		
		if (adminRepo.findAdminByAdminUsernameAndAdminPassword(input.getAdminUsername(), input.getAdminPassword()) == null) {
			Admin admin = new Admin();
			admin.setAdminUsername(input.getAdminUsername());
//			admin.setAdminName(input.getAdminName());
			admin.setAdminPassword(this.hash(input.getAdminPassword()));
			admin.setCreatedBy(createdBy);
			admin.setAdminCode(0);
			
			
			adminRepo.save(admin);
			return 0;
		} 
		
		return 1;
	}
}
