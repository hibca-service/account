package bit5.team2.account.service;

import bit5.team2.library.base.PagingProperties;
import bit5.team2.library.entity.User;

public interface UserService {
    PagingProperties<User> getUser(PagingProperties<User> pagingProperties);
}
