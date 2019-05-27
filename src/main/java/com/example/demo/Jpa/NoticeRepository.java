package com.example.demo.Jpa;

import com.example.demo.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author 练续强
 * @Description TODO()
 * @Date Create in 20:49 2019/4/15
 * @Modified By:
 */
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    List<Notice> findByExpert_User_Id(Integer user_id);

    List<Notice> findByExpert_User_IdAndAndIsRead(Integer user_id, Integer isRead);

    @Transactional
    @Modifying
    @Query("update Notice u set u.isRead = ?1 where u.id = ?2")
    int modifyIsReadById(Integer isRead, Integer id);
}
