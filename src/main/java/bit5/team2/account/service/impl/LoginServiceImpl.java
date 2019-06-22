package bit5.team2.account.service.impl;

import bit5.team2.account.repo.ProfileRepo;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.LoginService;
import bit5.team2.library.base.BaseService;
import bit5.team2.library.entity.Profile;
import bit5.team2.library.entity.User;
import bit5.team2.library.output.account.OutLoginMobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
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
        if (profileOptional.isPresent()) {
            Optional<User> userOptional = userRepo.findUserByUserId(profileOptional.get().getUserId());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                HashMap<String, Object> map = new HashMap<>();
                map.put("type", "user token");
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
        } else {
            return null;
        }
    }
}
