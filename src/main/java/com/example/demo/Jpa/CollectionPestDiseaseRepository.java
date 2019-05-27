package com.example.demo.Jpa;

import com.example.demo.entity.CollectionPestDisease;
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
public interface CollectionPestDiseaseRepository extends JpaRepository<CollectionPestDisease,Integer> {
    List<CollectionPestDisease> findByUserId(Integer user_id);
    List<CollectionPestDisease> findByUserIdAndName(Integer user_id, String name);
    @Transactional
    void deleteByUserIdAndName(Integer user_id,String name);

    @Transactional
    @Modifying
    @Query("delete from CollectionPestDisease where id in(:ids)")
    void batchDeleteIds(@Param("ids") List<Integer> ids);
}
