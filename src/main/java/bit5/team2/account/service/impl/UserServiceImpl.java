package bit5.team2.account.service.impl;

import bit5.team2.account.repo.ProfileRepo;
import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.UserService;
import bit5.team2.library.base.BaseService;
import bit5.team2.library.base.PagingProperties;
import bit5.team2.library.entity.User;
import bit5.team2.library.view.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends BaseService implements UserService {
    @Autowired
    ProfileRepo profileRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public PagingProperties<Profile> getUser(PagingProperties<Profile> pagingProperties) {
        Pageable pageable = PageRequest.of(pagingProperties.getPage(),pagingProperties.getPageSize(),pagingProperties.getDirection(),pagingProperties.getOrderBy());
        Page<Profile> profiles = profileRepo.findByKey(pageable,pagingProperties.getSearchKey());

        pagingProperties.setContent(profiles.getContent().size() == 0 ? null : profiles.getContent());
        pagingProperties.setTotalPage(profiles.getTotalPages());
        pagingProperties.setTotalData(profiles.getTotalElements());

        return pagingProperties;
    }

    @Override
    public PagingProperties<Profile> getUser(PagingProperties<Profile> pagingProperties, List<String> userIds) {
        Pageable pageable = PageRequest.of(pagingProperties.getPage(),pagingProperties.getPageSize(),pagingProperties.getDirection(),pagingProperties.getOrderBy());
        Page<Profile> profiles = profileRepo.findByKeyAndUserId(pageable,pagingProperties.getSearchKey(),userIds);

        pagingProperties.setContent(profiles.getContent().size() == 0 ? null : profiles.getContent());
        pagingProperties.setTotalPage(profiles.getTotalPages());
        pagingProperties.setTotalData(profiles.getTotalElements());

        return pagingProperties;
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
}
