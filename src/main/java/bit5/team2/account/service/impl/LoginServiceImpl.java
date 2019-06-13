package bit5.team2.account.service.impl;

import bit5.team2.account.lib.BaseService;

import bit5.team2.account.lib.JWT;
import bit5.team2.account.model.entity.Admin;
import bit5.team2.account.model.entity.User;
import bit5.team2.account.model.output.OutLogin;
import bit5.team2.account.repo.AdminRepo;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginServiceImpl extends BaseService implements LoginService {
    @Autowired
    UserRepo userRepo;
    
    @Autowired
    AdminRepo adminRepo;

    @Value("${refresh_token_duration}")
    private int refreshTokenDuration;

    @Value("${access_token_duration}")
    private int accessTokenDuration;

    @Override
    public OutLogin login(String username, String password) {
        String hashedPassword = this.hash(password);
        if (hashedPassword == null) {
            return null;
        }

        //check is the user admin first then check regular user
        OutLogin output = this._loginWeb(username,hashedPassword);
        if (output != null) {
        	return output;
        } else {
        	output = this._loginMobile(username,hashedPassword);
        	if (output != null) {
        		return output;
        	}
        }
        
        return null;
    }

    private OutLogin _loginMobile(String username, String password) {
        User user = userRepo.findByUsernameAndPassword(username,password);
        if (user == null) {
            return null;
        } else {
            JWT jwt = new JWT();
            HashMap<String, Object> map = new HashMap<>();
            map.put("userId",user.getId());
            map.put("username",username);
            String refresh = jwt.generateToken(map,false,this.refreshTokenDuration);
            map.put("name",user.getName());
            map.put("dateOfBirth",user.getDateOfBirth());
            map.put("purpose",user.getPurpose());
            map.put("oa",user.isOa());
            String access = jwt.generateToken(map,false,this.accessTokenDuration);

            OutLogin token = new OutLogin();
            token.setAccessToken(access);
            token.setRefreshToken(refresh);
            token.setSuperAdmin(false);

            return token;
        }
    }

    private OutLogin _loginWeb(String username, String password) {
    	Admin admin = adminRepo.findByUsernameAndPassword(username, password);
        if (admin == null) {
            return null;
        } else {
            JWT jwt = new JWT();
            HashMap<String, Object> map = new HashMap<>();
            map.put("userId",admin.getId());
            map.put("username",username);
            String refresh = jwt.generateToken(map,false,this.refreshTokenDuration);
            map.put("superAdmin", admin.isSuperAdmin());
            String access = jwt.generateToken(map,false,this.accessTokenDuration);

            OutLogin token = new OutLogin();
            token.setAccessToken(access);
            token.setRefreshToken(refresh);
            token.setSuperAdmin(admin.isSuperAdmin());

            return token;
        }
    }
}
