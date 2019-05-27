package com.example.demo.Jpa;

import com.example.demo.entity.CollectionNews;
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
public interface CollectionNewsRepository extends JpaRepository<CollectionNews,Integer> {
    List<CollectionNews> findByUserId(Integer user_id);
    List<CollectionNews> findByUserIdAndLinkUrl(Integer user_id, String linkUrl);
    @Transactional
    void deleteByUserIdAndLinkUrl(Integer user_id, String linkUrl);

    @Transactional
    @Modifying
    @Query("delete from CollectionNews where id in(:ids)")
    void batchDeleteIds(@Param("ids") List<Integer> ids);
}
