package com.example.demo.Jpa;

import com.example.demo.entity.ExpertApply;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 练续强
 * @Description TODO()
 * @Date Create in 14:11 2019/5/7
 * @Modified By:
 */
public interface ExpertApplyRepository extends JpaRepository<ExpertApply, Integer> {
    Boolean existsByUser_Id(Integer user_id);
    ExpertApply findByUser_Id(Integer user_id);
}
