package bit5.team2.account.repo;

import bit5.team2.library.entity.UserFollow;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepo extends PagingAndSortingRepository<UserFollow,String> {
    List<UserFollow> findByFollowedUserIdAndFollowEndDateIsNull(String userId);
}
