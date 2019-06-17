package bit5.team2.account.repo;

import bit5.team2.library.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User,String> {
	List<User> findUserByUsernameOrPhoneNumber(String username,String phoneNumber);

	Optional<User> findUserByUsernameAndPasswordAndFirebaseTokenIsNotNullAndFirebaseUUIDIsNotNull(String username, String password);

	Optional<User> findUserByUsernameAndFirebaseTokenIsNotNullAndFirebaseUUIDIsNotNull(String username);

	Optional<User> findUserByUserIdAndFirebaseTokenIsNullAndFirebaseUUIDIsNull(String userId);
}
