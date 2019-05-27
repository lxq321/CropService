package com.example.demo.Jpa;

import com.example.demo.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 练续强
 * @Description TODO()
 * @Date Create in 14:11 2019/5/7
 * @Modified By:
 */
public interface GoodRepository extends JpaRepository<Good,Integer> {
}
