package bit5.team2.account.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit5.team2.account.model.input.InCreateCoAdmin;
import bit5.team2.account.model.input.InDeleteCoAdmin;
import bit5.team2.account.model.input.InUpdateCoAdmin;
import bit5.team2.account.model.output.OutReadCoAdmin;
import bit5.team2.account.repo.AdminRepo;
import bit5.team2.account.service.AdminService;
import bit5.team2.library.base.BaseService;
import bit5.team2.library.entity.Admin;

@Service
public class AdminServiceImpl extends BaseService implements AdminService {
	@Autowired
	AdminRepo adminRepo;
	
	@PersistenceContext
    EntityManager entityManager;
	
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
	
	public List<OutReadCoAdmin> readCoAdmin() {
		List<OutReadCoAdmin> output = new ArrayList<OutReadCoAdmin>();
		OutReadCoAdmin temp = new OutReadCoAdmin();
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Admin> criteriaQuery = criteriaBuilder.createQuery(Admin.class);
		
		Root<Admin> adminRoot = criteriaQuery.from(Admin.class);
		
		Predicate predicateForOwnUserId = criteriaBuilder.equal(adminRoot.get("adminCode"),0);
		
		criteriaQuery.where(predicateForOwnUserId);
		List<Admin> admins = entityManager.createQuery(criteriaQuery).getResultList();
		
		for (int i = 0; i<admins.size();i++) {
			temp.setAdminId(admins.get(i).getAdminId());
//			temp.setAdminName(admins.get(i).getAdminName());
			temp.setAdminUsername(admins.get(i).getAdminUsername());
			temp.setCreatedBy(admins.get(i).getCreatedBy());
//			temp.setCreatedDate(admins.get(i).getCreatedDate());
			
			output.add(temp);
		}
		
		return output;
	}
	
	public boolean updateCoAdmin(InUpdateCoAdmin input, String adminId) {
		Admin admin = adminRepo.findAdminByAdminId(adminId);
		
		if (admin != null) {
			int flag = 0;
//			if (input.getAdminName() != null && input.getAdminName() != "") {
//				admin.setAdminName(input.getAdminName());
//				flag = 1;
//			}
			
			if (input.getAdminPassword() != null && input.getAdminPassword() != "" ) {
				admin.setAdminPassword(this.hash(input.getAdminPassword()));
				flag = 1;
			}
			
			//if lastUpdateDate is implemented in admin entity
//			if (flag == 1) {
//				Date date= new Date();
//				Timestamp ts = new Timestamp(date.getTime());
//				admin.setLastUpdateDate(ts);
//			}
			
			adminRepo.save(admin);
			
			return true;
		} else {
			return false;
		}
	}
	
	public boolean suspendCoAdmin(InDeleteCoAdmin input) {
		Admin admin = adminRepo.findAdminByAdminUsername(input.getAdminUsername());
		
		if (admin != null) {
			//if isactive parameter is available
//			if (input.isActive() == true) {
//				admin.setActive(true);
//			} else {
//				admin.setActive(false);
//			}
			return true;
		} else {
			return false;
		}
	}
}
