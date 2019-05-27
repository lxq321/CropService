package com.example.demo.Jpa;

import com.example.demo.entity.CollectionEvaluation;
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
public interface CollectionEvaluationRepository extends JpaRepository<CollectionEvaluation, Integer> {
    List<CollectionEvaluation> findByUserId(Integer user_id);

    List<CollectionEvaluation> findByEvaluationId(Integer evaluation_id);

    List<CollectionEvaluation> findByUserIdAndEvaluationId(Integer user_id, Integer evaluation_id);

    @Transactional
    void deleteByUserIdAndEvaluationId(Integer user_id, Integer evaluation_id);

    @Transactional
    @Modifying
    @Query("delete from CollectionEvaluation where evaluation_id in(:userIds)")
    void batchDeleteUser(@Param("userIds") List<Integer> userIds);
}
