package bit5.team2.account.service;

import bit5.team2.library.base.PagingProperties;
import bit5.team2.library.entity.User;
import bit5.team2.library.output.social.Profiles;
import bit5.team2.library.view.Profile;

import java.util.Optional;

public interface UserService {
    PagingProperties<Profiles> getUser(PagingProperties<Profiles> pagingProperties, String userId);

    PagingProperties<User> getUser(PagingProperties<User> pagingProperties, Optional<Boolean> isOa, Optional<Boolean>  isOaApproved);

    boolean changeAccountStatus(String userId, boolean isSuspended);

    boolean changeOAStatus(String userId, boolean isApprove);
}
