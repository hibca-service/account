package bit5.team2.account.service.impl;

import bit5.team2.account.repo.UserRepo;
import bit5.team2.account.service.UserService;
import bit5.team2.library.base.BaseService;
import bit5.team2.library.base.PagingProperties;
import bit5.team2.library.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseService implements UserService {
    @Autowired
    UserRepo userRepo;

    @Override
    public PagingProperties<User> getUser(PagingProperties<User> pagingProperties) {
        Pageable pageable = PageRequest.of(pagingProperties.getPage(),pagingProperties.getPageSize(),pagingProperties.getDirection(),pagingProperties.getOrderBy());
        Page<User> users = userRepo.findByKey(pageable,pagingProperties.getSearchKey());

        pagingProperties.setContent(users.getContent().size() == 0 ? null : users.getContent());
        pagingProperties.setTotalPage(users.getTotalPages());
        pagingProperties.setTotalData(users.getTotalElements());

        return pagingProperties;
    }
}
