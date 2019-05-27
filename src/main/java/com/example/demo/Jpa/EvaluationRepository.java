package com.example.demo.Jpa;

import com.example.demo.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 练续强
 * @Description TODO()
 * @Date Create in 20:49 2019/4/15
 * @Modified By:
 */
public interface EvaluationRepository extends JpaRepository<Evaluation,Integer> {
    List<Evaluation> findByUserId(Integer user_id);
    List<Evaluation> findByCropName(String cropName);
    List<Evaluation> findByCropNameLike(String cropName);
    List<Evaluation> findByContentLike(String content);
//    List<Evaluation> findByCropNameLikeOrContentLike(String cropName, String content, Pageable pageable);

}
