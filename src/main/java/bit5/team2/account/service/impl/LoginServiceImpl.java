package bit5.team2.account.service.impl;

import bit5.team2.account.model.output.OutLoginMobile;
import bit5.team2.account.repo.ProfileRepo;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.LoginService;
import bit5.team2.library.base.BaseService;
import bit5.team2.library.entity.User;
import bit5.team2.library.view.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Optional;

@Service
@Transactional
public class LoginServiceImpl extends BaseService implements LoginService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    ProfileRepo profileRepo;

    @Override
    public OutLoginMobile login(String username, String password) {
        String hashedPassword = this.hash(password);
        if (hashedPassword == null) {
            return null;
        }

        Optional<Profile> profileOptional = profileRepo.findProfileByUsernameAndPassword(username, hashedPassword);
        return profileOptional.map(profile -> this.generateToken(profile.getUserId())).orElse(null);
    }

    @Override
    public OutLoginMobile generateToken(String userId) {
        Optional<User> userOptional = userRepo.findUserByUserId(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            HashMap<String, Object> map = new HashMap<>();
            map.put("type", "user token");
            map.put("userId", user.getUserId());
            String refresh = this.createRefreshToken(map, false);
            map.put("username", user.getUsername());
            map.put("name", user.getName());
            map.put("dateOfBirth", user.getDateOfBirth());
            map.put("purpose", user.getPurpose());
            map.put("oa", user.getOa());
            String access = this.createAccessToken(map, false);

            OutLoginMobile token = new OutLoginMobile();
            token.setAccessToken(access);
            token.setRefreshToken(refresh);

            return token;
        } else {
            return null;
        }
    }

    @Override
    public OutLoginMobile generateToken(String userId, String refreshToken) {
        Optional<User> userOptional = userRepo.findUserByUserId(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            HashMap<String, Object> map = new HashMap<>();
            map.put("type", "user token");
            map.put("userId", user.getUserId());
            map.put("username", user.getUsername());
            map.put("name", user.getName());
            map.put("dateOfBirth", user.getDateOfBirth());
            map.put("purpose", user.getPurpose());
            map.put("oa", user.getOa());
            String access = this.createRefreshToken(map, false);

            OutLoginMobile token = new OutLoginMobile();
            token.setAccessToken(access);
            token.setRefreshToken(refreshToken);

            return token;
        } else {
            return null;
        }
    }
}
