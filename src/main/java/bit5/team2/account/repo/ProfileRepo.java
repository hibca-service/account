package bit5.team2.account.repo;

import bit5.team2.library.view.ProfileView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepo extends PagingAndSortingRepository<ProfileView,String> {
	Optional<ProfileView> findProfileViewByUsername(String userId);

	Optional<ProfileView> findProfileViewByUsernameAndPassword(String userId, String password);

	@Query("select p from ProfileView p where p.name like %:key% or p.username like %:key% or p.phoneNumber like %:key%")
	Page<ProfileView> findByKey(Pageable pageable, @Param("key") String key);
}
