package bit5.team2.account.repo;

import bit5.team2.library.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User,String> {
	Optional<User> findUserByUserId(String userId);

	Optional<User> findUserByUsernameAndFirebaseTokenIsNotNullAndFirebaseUUIDIsNotNull(String username);

	Optional<User> findUserByUserIdAndFirebaseTokenIsNullAndFirebaseUUIDIsNull(String userId);
	
	User findByUsername(String username);
}
