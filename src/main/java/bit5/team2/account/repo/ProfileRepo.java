package bit5.team2.account.repo;

import bit5.team2.library.entity.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepo extends PagingAndSortingRepository<Profile,String> {
	Optional<Profile> findUserByUsernameAndFirebaseTokenIsNotNullAndFirebaseUUIDIsNotNull(String userId);
}
