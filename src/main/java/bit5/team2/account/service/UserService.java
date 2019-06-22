package bit5.team2.account.service;

import bit5.team2.library.base.PagingProperties;
import bit5.team2.library.entity.Profile;

public interface UserService {
    PagingProperties<Profile> getUser(PagingProperties<Profile> pagingProperties);
}
