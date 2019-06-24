package bit5.team2.account.repo;

import bit5.team2.library.view.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepo extends PagingAndSortingRepository<Profile,String> {
	Optional<Profile> findProfileByUsername(String userId);

	Optional<Profile> findProfileByUsernameAndPassword(String userId,String password);

	List<Profile> findProfileByUsernameOrPhoneNumber(String userId, String phoneNumber);

	@Query("select p from Profile p where p.name like %:key% or p.username like %:key% or p.phoneNumber like %:key%")
	Page<Profile> findByKey(Pageable pageable, @Param("key") String key);

	@Query("select p from Profile p where p.userId in :userId and (p.name like %:key% or p.username like %:key% or p.phoneNumber like %:key%)")
	Page<Profile> findByKeyAndUserId(Pageable pageable, @Param("key") String key, @Param("userId") List<String> userId);
}
