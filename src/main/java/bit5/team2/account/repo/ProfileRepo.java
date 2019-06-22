package bit5.team2.account.repo;

import bit5.team2.library.entity.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepo extends PagingAndSortingRepository<Profile,String> {
	Optional<Profile> findProfileByUsername(String userId);

	Optional<Profile> findProfileByUsernameAndPassword(String userId,String password);

	List<Profile> findProfileByUsernameOrPhoneNumber(String userId, String phoneNumber);
}
