package bit5.team2.account.service.impl;

import bit5.team2.account.lib.BaseService;
import bit5.team2.account.lib.JWT;
import bit5.team2.account.model.entity.User;
import bit5.team2.account.model.output.OutLogin;
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

        OutLogin output = this._loginMobile(username,hashedPassword);
        if (output == null) {
            return this._loginWeb(username,hashedPassword);
        } else {
            return output;
        }
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
            map.put("finished",user.isFinished());
            String access = jwt.generateToken(map,false,this.accessTokenDuration);

            OutLogin token = new OutLogin();
            token.setAccessToken(access);
            token.setRefreshToken(refresh);

            return token;
        }
    }

    private OutLogin _loginWeb(String username, String password) {
        return null;
    }
}
