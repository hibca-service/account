package bit5.team2.account.repo;

import bit5.team2.library.entity.Admin;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends PagingAndSortingRepository<Admin,String> {
    Optional<Admin> findAdminByAdminId(String adminId);
}
