package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Jpa.CollectionEvaluationRepository;
import com.example.demo.entity.CollectionEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api/collectionEvaluation")
public class CollectionEvaluationController {

    @Autowired
    private CollectionEvaluationRepository collectionEvaluationRepository;

    @RequestMapping("list")
    public List<CollectionEvaluation> list() {
        return collectionEvaluationRepository.findAll();
    }

    @RequestMapping("save")
    public void save(String collectionEvaluation) {
        CollectionEvaluation model = JSON.parseObject(collectionEvaluation, CollectionEvaluation.class);
        collectionEvaluationRepository.save(model);
    }

    @RequestMapping("deleteById")
    public void deleteById(Integer id) {
        collectionEvaluationRepository.deleteById(id);
    }

    @RequestMapping("deleteByEvaluationIdIn")
    public void deleteByEvaluationIdIn(String ids) {
        List<Integer> integers = JSON.parseArray(ids, Integer.class);
        collectionEvaluationRepository.batchDeleteUser(integers);
    }

    @RequestMapping("update")
    public void update(CollectionEvaluation collectionEvaluation) {
        collectionEvaluationRepository.save(collectionEvaluation);
    }

    @RequestMapping("findById")
    public CollectionEvaluation findById(Integer id) {
        return collectionEvaluationRepository.findById(id).get();
    }

    @RequestMapping("findByUserId")
    public List<CollectionEvaluation> findByUserId(Integer id) {
        return collectionEvaluationRepository.findByUserId(id);
    }

    @RequestMapping("findByEvaluationId")
    public List<CollectionEvaluation> findByEvaluationId(Integer id) {
        return collectionEvaluationRepository.findByEvaluationId(id);
    }
    @RequestMapping("findByUserIdAndEvaluationId")
    public CollectionEvaluation findByUserIdAndEvaluationId(Integer userId, Integer evaluationId) {
        List<CollectionEvaluation> collectionEvaluations = collectionEvaluationRepository.findByUserIdAndEvaluationId(userId,evaluationId);
        if (!collectionEvaluations.isEmpty()){
            return collectionEvaluations.get(0);
        }else {
            return null;
        }
    }
    @RequestMapping("findIsCollectionByUserIdAndEvaluationId")
    public Map<String, Object> findIsCollectionByUserIdAndEvaluationId(Integer userId, Integer evaluationId) {
        CollectionEvaluation collectionEvaluation = findByUserIdAndEvaluationId(userId,evaluationId);
        Map<String, Object> r = new HashMap<>();
        if (collectionEvaluation ==null ) {
            r.put("flag", 1);
        } else {
            r.put("flag", 0);
        }
        return r;
    }

    @RequestMapping("deleteByUserIdAndEvaluationId")
    public void deleteByUserIdAndEvaluationId(Integer userId,Integer evaluationId) {
        collectionEvaluationRepository.deleteByUserIdAndEvaluationId(userId,evaluationId);
    }

}
