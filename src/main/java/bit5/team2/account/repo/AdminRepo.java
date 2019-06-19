package bit5.team2.account.repo;

import bit5.team2.library.entity.Admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends PagingAndSortingRepository<Admin,String> {
    Admin findAdminByAdminUsernameAndAdminPassword(String username,String password);
    
	@Query("select s from Admin s where (adminName like %:key% or adminUsername like %:key%) ")
	Page<Admin> findByKey(Pageable pageable, @Param("key") String key);
}
