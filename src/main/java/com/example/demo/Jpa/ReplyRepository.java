package com.example.demo.Jpa;

import com.example.demo.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author 练续强
 * @Description TODO()
 * @Date Create in 20:49 2019/4/15
 * @Modified By:
 */
public interface ReplyRepository extends JpaRepository<Reply,Integer> {
    @Transactional
    List<Reply> findByEvaluationId(Integer evaluation_id);
    List<Reply> findByUserId(Integer user_id);
}
