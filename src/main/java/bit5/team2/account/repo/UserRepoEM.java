package bit5.team2.account.repo;

import bit5.team2.library.base.PagingProperties;
import bit5.team2.library.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepoEM {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public PagingProperties<User> getUser(PagingProperties<User> pagingProperties, Boolean oa, Boolean oaApproved, Boolean active, List<String> userId) {
        Pageable pageable = PageRequest.of(pagingProperties.getPage(), pagingProperties.getPageSize(), pagingProperties.getDirection(), pagingProperties.getOrderBy());
        String searchKey = pagingProperties.getSearchKey();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        List<Predicate> combined = new ArrayList<>();
        if (oa != null) {
            combined.add(criteriaBuilder.equal(root.get("oa"), oa ? "1" : "0"));
        }
        if (oaApproved != null) {
            if (oaApproved) {
                combined.add(criteriaBuilder.equal(root.get("oaApprove"), "1"));
            } else {
                combined.add(
                        criteriaBuilder.or(
                                criteriaBuilder.equal(root.get("oaApprove"), "0"),
                                criteriaBuilder.isNull(root.get("oaApprove"))
                        )
                );
            }
        }
        if (active != null) {
            combined.add(criteriaBuilder.equal(root.get("active"), active ? "1" : "0"));
        }
        if (searchKey != null && !searchKey.equals("")) {
            searchKey = searchKey.replaceAll("%", "\\%").replaceAll("_", "\\_");
            searchKey = '%' + searchKey + '%';
            Predicate preSearchKey1 = criteriaBuilder.like(root.get("name"), searchKey, '\\');
            Predicate preSearchKey2 = criteriaBuilder.like(root.get("username"), searchKey, '\\');
            Predicate preSearchKey3 = criteriaBuilder.like(root.get("phoneNumber"), searchKey, '\\');
            combined.add(criteriaBuilder.or(preSearchKey1, preSearchKey2, preSearchKey3));
        }

        combined.add(criteriaBuilder.isNotNull(root.get("firebaseToken")));
        combined.add(criteriaBuilder.isNotNull(root.get("firebaseUUID")));

        query.select(root).where(combined.toArray(new Predicate[0]));
        List<User> resultList = entityManager.createQuery(query).getResultList();

        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > resultList.size() ? resultList.size() : (start + pageable.getPageSize());

        Page<User> data = new PageImpl<>(resultList.subList(start, end), pageable, resultList.size());

        pagingProperties.setContent(data.getContent().size() == 0 ? null : data.getContent());
        pagingProperties.setTotalPage(data.getTotalPages());
        pagingProperties.setTotalData(data.getTotalElements());

        return pagingProperties;
    }
}
