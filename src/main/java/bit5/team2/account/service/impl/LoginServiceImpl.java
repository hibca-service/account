package bit5.team2.account.service.impl;

import bit5.team2.account.lib.JWT;
import bit5.team2.account.model.entity.User;
import bit5.team2.account.model.output.Token;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserRepo userRepo;

    @Value("${refresh_token_duration}")
    private int refreshTokenDuration;

    @Value("${access_token_duration}")
    private int accessTokenDuration;

    @Override
    public Token login(String username, String password) {
        String hashedPassword = this._hash(password);
        if (hashedPassword == null) {
            return null;
        }

        Token output = this._loginMobile(username,hashedPassword);
        if (output == null) {
            return this._loginWeb(username,hashedPassword);
        }
        else {
            return output;
        }
    }

    @Override
    public void set() {
        User user = new User();
        user.setUsername("dharmawan");
        user.setPassword(this._hash("123"));
        userRepo.save(user);
    }

    private String _bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private String _hash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return _bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private Token _loginMobile(String username, String password) {
        User user = userRepo.findByUsernameAndPassword(username,password);
        if (user == null) {
            return null;
        }
        else {
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

            Token token = new Token();
            token.setAccessToken(access);
            token.setRefreshToken(refresh);

            return token;
        }
    }

    private Token _loginWeb(String username, String password) {
        return null;
    }
}
