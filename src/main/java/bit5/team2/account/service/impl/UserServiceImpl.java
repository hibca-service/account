package bit5.team2.account.service.impl;

import bit5.team2.account.model.output.Profiles;
import bit5.team2.account.repo.FollowRepo;
import bit5.team2.account.repo.ProfileRepo;
import bit5.team2.account.repo.UserRepoEM;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.UserService;
import bit5.team2.library.base.BaseService;
import bit5.team2.library.base.PagingProperties;
import bit5.team2.library.entity.User;
import bit5.team2.library.entity.UserFollow;
import bit5.team2.library.view.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl extends BaseService implements UserService {
    @Autowired
    ProfileRepo profileRepo;

    @Autowired
    UserRepoEM userRepoEM;

    @Autowired
    UserRepo userRepo;

    @Autowired
    FollowRepo followRepo;

    @Override
    public PagingProperties<Profiles> getUser(PagingProperties<Profiles> pagingProperties, String userLogin) {
        Pageable pageable = PageRequest.of(pagingProperties.getPage(),pagingProperties.getPageSize(),pagingProperties.getDirection(),pagingProperties.getOrderBy());
        Page<Profile> profiles = profileRepo.findByKey(pageable,pagingProperties.getSearchKey());

        List<String> followed = this._getFollowing(userLogin);

        List<Profiles> profilesList = new ArrayList<>();
        for(Profile profile : profiles) {
            Profiles temp = new Profiles();
            temp.setUserId(profile.getUserId());
            temp.setOa(profile.getOa());
            temp.setUsername(profile.getUsername());
            temp.setName(profile.getName());
            temp.setPathProfilePicture(profile.getPathProfilePicture());
            temp.setDateOfBirth(profile.getDateOfBirth());
            temp.setCityOfBirth(profile.getCityOfBirth());
            temp.setUserGender(profile.getUserGender());
            temp.setPhoneNumber(profile.getPhoneNumber());
            temp.setPurpose(profile.getPurpose());
            temp.setOaApprove(profile.getOaApprove());
            temp.setActive(profile.getActive());
            temp.setFirebaseToken(profile.getFirebaseToken());
            temp.setFirebaseUUID(profile.getFirebaseUUID());
            temp.setFollower(profile.getFollower());
            temp.setFollowing(profile.getFollowing());
            temp.setAlreadyFollowed(profile.getUserId().equals(userLogin) ? null : followed.contains(profile.getUserId()));
            profilesList.add(temp);
        }

        pagingProperties.setContent(profilesList.size() == 0 ? null : profilesList);
        pagingProperties.setTotalPage(profiles.getTotalPages());
        pagingProperties.setTotalData(profiles.getTotalElements());

        return pagingProperties;
    }

    @Override
    public PagingProperties<User> getUser(PagingProperties<User> pagingProperties, Optional<Boolean> isOa, Optional<Boolean>  isOaApproved, Optional<Boolean>  isActive) {
        return userRepoEM.getUser(pagingProperties, isOa.orElse(null),isOaApproved.orElse(null),isActive.orElse(null), null);
    }

    @Override
    public boolean changeAccountStatus(String userId, boolean isActive) {
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setActive(isActive ? "1" : "0");
            userRepo.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean changeOAStatus(String userId,boolean isApprove) {
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setOaApprove(isApprove ? "1" : "0");
            userRepo.save(user);
            return true;
        } else {
            return false;
        }
    }

    private List<String> _getFollowing(String userId) {
        List<UserFollow> userFollows = followRepo.findByFollowedUserIdAndFollowEndDateIsNull(userId);
        List<String> userIds = new ArrayList<>();
        if ( ! userFollows.isEmpty()) {
            for (UserFollow userFollow : userFollows) {
                userIds.add(userFollow.getFollowingUserId());
            }
        }
        return userIds;
    }

}
