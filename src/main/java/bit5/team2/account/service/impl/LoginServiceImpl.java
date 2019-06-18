package bit5.team2.account.service.impl;

import bit5.team2.account.repo.AdminRepo;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.LoginService;
import bit5.team2.library.base.BaseService;
import bit5.team2.library.entity.Admin;
import bit5.team2.library.entity.User;
import bit5.team2.library.output.account.OutLoginMobile;
import bit5.team2.library.output.account.OutLoginWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class LoginServiceImpl extends BaseService implements LoginService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    AdminRepo adminRepo;

    @Override
    public Object login(String username, String password) {
        String hashedPassword = this.hash(password);
        if (hashedPassword == null) {
            return null;
        }

        //check is the user admin first then check regular user
        OutLoginWeb web = this._loginWeb(username, hashedPassword);
        if (web != null) {
            return web;
        } else {
            return this._loginMobile(username, hashedPassword);
        }

    }

    private OutLoginMobile _loginMobile(String username, String password) {
        Optional<User> userOptional = userRepo.findUserByUsernameAndPasswordAndFirebaseTokenIsNotNullAndFirebaseUUIDIsNotNull(username, password);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            HashMap<String, Object> map = new HashMap<>();
            map.put("userId", user.getUserId());
            map.put("username", username);
            String refresh = this.createRefreshToken(map, false);
            map.put("name", user.getName());
            map.put("dateOfBirth", user.getDateOfBirth());
            map.put("purpose", user.getPurpose());
            map.put("oa", user.getOa());
            String access = this.createRefreshToken(map, false);

            OutLoginMobile token = new OutLoginMobile();
            token.setAccessToken(access);
            token.setRefreshToken(refresh);

            return token;
        } else {
            return null;
        }
    }

    private OutLoginWeb _loginWeb(String username, String password) {
        Admin admin = adminRepo.findAdminByAdminUsernameAndAdminPassword(username, password);
        if (admin == null) {
            return null;
        } else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("userId", admin.getAdminId());
            map.put("username", username);
            String refresh = this.createRefreshToken(map, true);
            map.put("superAdmin", admin.getAdminCode());
            String access = this.createAccessToken(map, true);

            OutLoginWeb token = new OutLoginWeb();
            token.setAccessToken(access);
            token.setRefreshToken(refresh);
            token.setSuperAdmin(admin.getAdminCode() == 1);

            return token;
        }
    }
}
