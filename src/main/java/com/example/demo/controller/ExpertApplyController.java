package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Jpa.ExpertApplyRepository;
import com.example.demo.entity.ExpertApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 练续强
 * @Description TODO()
 * @Date Create in 14:12 2019/5/7
 * @Modified By:
 */
@RestController
@RequestMapping("/api/expertApply")
public class ExpertApplyController {
    @Autowired
    private ExpertApplyRepository expertApplyRepository;

    @PostMapping("save")
    public ExpertApply save(String expertApply) {
        ExpertApply entity = JSON.parseObject(expertApply, ExpertApply.class);
        return expertApplyRepository.save(entity);
    }

    @PostMapping("update")
    public void update(String expertApply) {
        save(expertApply);
    }
    @PostMapping("existsByUserId")
    public Map<String, Object> existsByUserId(Integer userId) {
        Map<String, Object> r = new HashMap<>();
        Boolean isApply = expertApplyRepository.existsByUser_Id(userId);
        r.put("isApply",isApply);
        if (isApply){
            ExpertApply expertApply = expertApplyRepository.findByUser_Id(userId);
            r.put("expertApply",JSON.toJSONString(expertApply));
        }
        return r;
    }

}
