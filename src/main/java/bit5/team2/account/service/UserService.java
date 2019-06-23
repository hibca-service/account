package bit5.team2.account.service;

import bit5.team2.library.base.PagingProperties;
import bit5.team2.library.view.Profile;

import java.util.List;

public interface UserService {
    PagingProperties<Profile> getUser(PagingProperties<Profile> pagingProperties);

    PagingProperties<Profile> getUser(PagingProperties<Profile> pagingProperties, List<String> userIds);

    boolean changeAccountStatus(String userId, boolean isSuspended);

    boolean changeOAStatus(String userId, boolean isApprove);
}
