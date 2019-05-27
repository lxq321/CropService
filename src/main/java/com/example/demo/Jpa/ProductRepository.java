package com.example.demo.Jpa;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 练续强
 * @Description TODO()
 * @Date Create in 20:49 2019/4/15
 * @Modified By:
 */
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByCompanyId(Integer company_id);
}
