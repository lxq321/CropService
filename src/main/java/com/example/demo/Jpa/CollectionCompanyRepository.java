package com.example.demo.Jpa;

import com.example.demo.entity.CollectionCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author 练续强
 * @Description TODO()
 * @Date Create in 20:49 2019/4/15
 * @Modified By:
 */
public interface CollectionCompanyRepository extends JpaRepository<CollectionCompany, Integer> {
    List<CollectionCompany> findByUserId(Integer user_id);

    List<CollectionCompany> findByCompanyId(Integer company_id);

    List<CollectionCompany> findByUserIdAndCompanyId(Integer user_id, Integer company_id);

    @Transactional
    void deleteByUserIdAndCompanyId(Integer user_id, Integer company_id);

    @Transactional
    @Modifying
    @Query("delete from CollectionCompany where company_id in(:userIds)")
    void batchDeleteUser(@Param("userIds") List<Integer> userIds);
}
