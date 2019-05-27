package com.example.demo.Jpa;

import com.example.demo.entity.CollectionExpert;
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
public interface CollectionExpertRepository extends JpaRepository<CollectionExpert, Integer> {
    List<CollectionExpert> findByUserId(Integer user_id);

    List<CollectionExpert> findByExpertId(Integer expert_id);

    List<CollectionExpert> findByUserIdAndExpertId(Integer user_id, Integer expert_id);

    @Transactional
    void deleteByUserIdAndExpertId(Integer user_id, Integer expert_id);

    @Transactional
    @Modifying
    @Query("delete from CollectionExpert where expert_id in(:userIds)")
    void batchDeleteUser(@Param("userIds") List<Integer> userIds);

    List<CollectionExpert> findByExpert_User_Id(Integer id);
}
