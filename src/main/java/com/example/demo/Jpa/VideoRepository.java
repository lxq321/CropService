package com.example.demo.Jpa;

import com.example.demo.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 练续强
 * @Description TODO()
 * @Date Create in 20:49 2019/4/15
 * @Modified By:
 */
public interface VideoRepository extends JpaRepository<Video,Integer> {

}
