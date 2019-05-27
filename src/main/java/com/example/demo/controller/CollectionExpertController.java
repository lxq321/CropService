package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Jpa.CollectionExpertRepository;
import com.example.demo.entity.CollectionExpert;
import com.example.demo.entity.Expert;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 练续强
 * @Description TODO()
 * @Date Create in 20:53 2019/4/15
 * @Modified By:
 */
@RestController
@RequestMapping("/api/collectionExpert")
public class CollectionExpertController {

    @Autowired
    private CollectionExpertRepository collectionExpertRepository;
    @Autowired
    private ExpertController expertController;

    @RequestMapping("list")
    public List<CollectionExpert> list() {
        return collectionExpertRepository.findAll();
    }

    @RequestMapping("save")
    public void save(String collectionExpert) {
        CollectionExpert model = JSON.parseObject(collectionExpert, CollectionExpert.class);
        collectionExpertRepository.save(model);
        Expert expert = expertController.findById(model.getExpert().getId());
        expert.setAttentionCount(expert.getAttentionCount() + 1);
        expertController.update(expert);
    }

    @RequestMapping("deleteById")
    public void deleteById(Integer id) {
        collectionExpertRepository.deleteById(id);
        Expert expert = expertController.findById(id);
        expert.setAttentionCount(expert.getAttentionCount() - 1);
        expertController.update(expert);
    }

    @RequestMapping("update")
    public void update(CollectionExpert collectionExpert) {
        collectionExpertRepository.save(collectionExpert);
    }

    @RequestMapping("findById")
    public CollectionExpert findById(Integer id) {
        return collectionExpertRepository.findById(id).get();
    }

    @RequestMapping("deleteByExpertIdIn")
    public void deleteByExpertIdIn(String ids) {
        List<Integer> integers = JSON.parseArray(ids, Integer.class);
        collectionExpertRepository.batchDeleteUser(integers);
        for (int id : integers) {
            Expert expert = expertController.findById(id);
            expert.setAttentionCount(expert.getAttentionCount() - 1);
            expertController.update(expert);
        }

    }

    @RequestMapping("findByUserId")
    public List<CollectionExpert> findByUserId(Integer id) {
        return collectionExpertRepository.findByUserId(id);
    }

    @RequestMapping("findByExpertId")
    public List<CollectionExpert> findByExpertId(Integer id) {
        return collectionExpertRepository.findByExpertId(id);
    }


    @RequestMapping("findByUserIdAndExpertId")
    public CollectionExpert findByUserIdAndExpertId(Integer userId, Integer expertId) {
        List<CollectionExpert> collectionExperts = collectionExpertRepository.findByUserIdAndExpertId(userId, expertId);
        if (!collectionExperts.isEmpty()) {
            return collectionExperts.get(0);
        } else {
            return null;
        }
    }

    @RequestMapping("findIsCollectionByUserIdAndExpertId")
    public Map<String, Object> findIsCollectionByUserIdAndExpertId(Integer userId, Integer expertId) {
        CollectionExpert collectionExpert = findByUserIdAndExpertId(userId, expertId);
        Map<String, Object> r = new HashMap<>();
        if (collectionExpert == null) {
            r.put("flag", 1);
        } else {
            r.put("flag", 0);
        }
        return r;
    }

    @RequestMapping("deleteByUserIdAndExpertId")
    public void deleteByUserIdAndExpertId(Integer userId, Integer expertId) {
        collectionExpertRepository.deleteByUserIdAndExpertId(userId, expertId);
        Expert expert = expertController.findById(expertId);
        expert.setAttentionCount(expert.getAttentionCount() - 1);
        expertController.update(expert);
    }


    /*  @RequestMapping("findAnswerQuestionsByExpertId")
    public void findByExample(String id, String currentPage, String pagesize, String sort) {
        Expert expert = new Expert();
        User user=new User();
        Role role=new Role();
        user.setRole(role);
        expert.setUser(user);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());

        //创建实例
        Example<Expert> example = Example.of(expert, matcher);

        //分页
        Pageable pageable = MyUtil.getPageable(currentPage, pagesize, Sort.Direction.DESC, sort);
         collectionExpertRepository.findAll(example, pageable).getContent();
    }*/
    @RequestMapping("findByExpertUserId")
    public List<User> findByExpertUserId(Integer id) {
        List<CollectionExpert> collectionExperts = collectionExpertRepository.findByExpert_User_Id(id);
        List<User> users=new ArrayList<>();
        for (CollectionExpert c:collectionExperts) {
            users.add(c.getUser());
        }
        return users;
    }
}
