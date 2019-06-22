package bit5.team2.account.service.impl;

import bit5.team2.account.repo.AdminRepo;
import bit5.team2.account.service.AdminLoginService;
import bit5.team2.library.base.BaseService;
import bit5.team2.library.entity.Admin;
import bit5.team2.library.output.account.OutLoginWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AdminLoginServiceImpl extends BaseService implements AdminLoginService {
    @Autowired
    AdminRepo adminRepo;

    @Override
    public OutLoginWeb reLogin(String adminId, String refreshToken) {
        Optional<Admin> adminOptional = adminRepo.findAdminByAdminId(adminId);
        return adminOptional.map(admin -> this._generateToken(admin, refreshToken)).orElse(null);
    }

    private OutLoginWeb _generateToken(Admin admin, String refreshToken) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("adminId", admin.getAdminId());
        map.put("adminname", admin.getAdminUsername());
        map.put("superAdmin", admin.getAdminCode());
        String access = this.createAccessToken(map, true);

        OutLoginWeb token = new OutLoginWeb();
        token.setAccessToken(access);
        token.setRefreshToken(refreshToken);
        token.setSuperAdmin(admin.getAdminCode() == 1);

        return token;
    }
}
