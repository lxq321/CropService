package com.example.demo.controller;

import com.example.demo.Jpa.VideoRepository;
import com.example.demo.entity.Video;
import com.example.demo.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 练续强
 * @Description TODO()
 * @Date Create in 20:53 2019/4/15
 * @Modified By:
 */
@RestController
@RequestMapping("/api/video")
public class VideoController {


    @Autowired
    private VideoRepository videoRepository;

 /*   @RequestMapping("list")
    public List<Video> list(){
        return videoRepository.findAll();
    }*/
    @RequestMapping("listPage")
    public List<Video> list(Integer currentPage,Integer pagesize){

        Pageable pageable = MyUtil.getPageable(currentPage, pagesize, Sort.Direction.DESC, "id");
        Page<Video> models = videoRepository.findAll(pageable);
        return models.getContent();
    }
    @RequestMapping("save")
    public void save(Video video){
       videoRepository.save(video);
    }
    @RequestMapping("deleteById")
    public void deleteById(Integer id){
       videoRepository.deleteById(id);
    }
    @RequestMapping("update")
    public void update(Video video){
        videoRepository.save(video);
    }
    @RequestMapping("findById")
    public Video findById(Integer id) {
        return videoRepository.findById(id).get();
    }
}
