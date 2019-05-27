package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Jpa.ExpertRepository;
import com.example.demo.Jpa.PostRepository;
import com.example.demo.entity.Expert;
import com.example.demo.entity.Post;
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
@RequestMapping("/api/post")
public class PostController {


    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ExpertRepository expertRepository;

 /*   @RequestMapping("list")
    public List<Post> list(){
        return postRepository.findAll();
    }*/
    @RequestMapping("listPage")
    public List<Post> list(Integer currentPage,Integer pagesize){
        Pageable pageable = MyUtil.getPageable(currentPage, pagesize, Sort.Direction.DESC, "id");
        Page<Post> models = postRepository.findAll(pageable);
        return models.getContent();
    }
    @RequestMapping("save")
    public void save(String post,Integer userId){
        Post post1 = JSON.parseObject(post, Post.class);
        Expert expert = expertRepository.findByUserId(userId).get(0);
        post1.setExpert(expert);
        postRepository.save(post1);
    }
    @RequestMapping("deleteById")
    public void deleteById(Integer id){
       postRepository.deleteById(id);
    }
    @RequestMapping("update")
    public void update(Post post){
        postRepository.save(post);
    }
    @RequestMapping("findById")
    public Post findById(Integer id) {
        return postRepository.findById(id).get();
    }
    @RequestMapping("findByExpertId")
    public List<Post> findByExpertId(Integer expert_id) {
        return postRepository.findByExpertId(expert_id);
    }
}
