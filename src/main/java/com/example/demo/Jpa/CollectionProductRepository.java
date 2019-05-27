package com.example.demo.Jpa;

import com.example.demo.entity.CollectionProduct;
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
public interface CollectionProductRepository extends JpaRepository<CollectionProduct, Integer> {
    List<CollectionProduct> findByUserId(Integer user_id);

    List<CollectionProduct> findByProductId(Integer product_id);

    List<CollectionProduct> findByUserIdAndProductId(Integer user_id, Integer product_id);

    @Transactional
    void deleteByUserIdAndProductId(Integer user_id, Integer product_id);

    @Transactional
    @Modifying
    @Query("delete from CollectionProduct where product_id in(:userIds)")
    void batchDeleteUser(@Param("userIds") List<Integer> userIds);
}
