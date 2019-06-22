package bit5.team2.account.service.impl;

import bit5.team2.account.repo.ProfileRepo;
import bit5.team2.account.service.UserService;
import bit5.team2.library.base.BaseService;
import bit5.team2.library.base.PagingProperties;
import bit5.team2.library.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseService implements UserService {
    @Autowired
    ProfileRepo profileRepo;

    @Override
    public PagingProperties<Profile> getUser(PagingProperties<Profile> pagingProperties) {
        Pageable pageable = PageRequest.of(pagingProperties.getPage(),pagingProperties.getPageSize(),pagingProperties.getDirection(),pagingProperties.getOrderBy());
        Page<Profile> profiles = profileRepo.findByKey(pageable,pagingProperties.getSearchKey());

        pagingProperties.setContent(profiles.getContent().size() == 0 ? null : profiles.getContent());
        pagingProperties.setTotalPage(profiles.getTotalPages());
        pagingProperties.setTotalData(profiles.getTotalElements());

        return pagingProperties;
    }
}
