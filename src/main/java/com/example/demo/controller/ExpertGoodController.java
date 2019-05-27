package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Jpa.ExpertGoodRepository;
import com.example.demo.Jpa.UserRepository;
import com.example.demo.entity.Expert;
import com.example.demo.entity.ExpertGood;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 练续强
 * @Description TODO()
 * @Date Create in 14:12 2019/5/7
 * @Modified By:
 */
@RestController
@RequestMapping("/api/expertGood")
public class ExpertGoodController {
    @Autowired
    private ExpertGoodRepository expertGoodRepository;
    @Autowired
    private ExpertController expertController;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("save")
    public void save(String expertGood) {
        ExpertGood entity = JSON.parseObject(expertGood, ExpertGood.class);
        //添加点赞
        ExpertGood save = expertGoodRepository.save(entity);
        //获得专家实体
        Expert expert = expertController.findById(entity.getExpert().getId());
        //点赞数加一
        expert.setLikeCount(expert.getLikeCount()+1);
        expertController.save(expert);
    }
    @PostMapping("isGood")
    public Boolean isGood(Integer userId,Integer expertId){
        User user=userRepository.findById(userId).get();
        Expert expert=expertController.findById(expertId);
        ExpertGood expertGood=new ExpertGood();
        expertGood.setExpert(expert);
        expertGood.setUser(user);
        Boolean isGood=expertGoodRepository.exists(Example.of(expertGood));
        return isGood;
    }
}
