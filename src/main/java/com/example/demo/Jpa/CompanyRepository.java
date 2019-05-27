package com.example.demo.Jpa;

import com.example.demo.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 练续强
 * @Description TODO()
 * @Date Create in 20:49 2019/4/15
 * @Modified By:
 */
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    List<Company> findByNameLike(String name);
}
