package bit5.team2.account.repo;

import bit5.team2.library.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User,String> {
	List<User> findUserByUsernameOrPhoneNumber(String username,String phoneNumber);

	Optional<User> findUserByUsernameAndPasswordAndFirebaseTokenIsNotNullAndFirebaseUUIDIsNotNull(String username, String password);

	Optional<User> findUserByUsernameAndFirebaseTokenIsNotNullAndFirebaseUUIDIsNotNull(String username);

	Optional<User> findUserByUserIdAndFirebaseTokenIsNullAndFirebaseUUIDIsNull(String userId);

	@Query("select s from User s where (name like %:key% or username like %:key% or phoneNumber like %:key%) and firebaseToken is not null and firebaseUUID is not null")
	Page<User> findByKey(Pageable pageable, @Param("key") String key);
}
