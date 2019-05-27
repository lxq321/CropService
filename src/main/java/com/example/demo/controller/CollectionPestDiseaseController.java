package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Jpa.CollectionPestDiseaseRepository;
import com.example.demo.entity.CollectionPestDisease;
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
@RequestMapping("/api/collectionPestDisease")
public class CollectionPestDiseaseController {

    @Autowired
    private CollectionPestDiseaseRepository collectionPestDiseaseRepository;

    @RequestMapping("list")
    public List<CollectionPestDisease> list() {
        return collectionPestDiseaseRepository.findAll();
    }

    @RequestMapping("save")
    public void save(String collectionPestDisease) {
        CollectionPestDisease model = JSON.parseObject(collectionPestDisease, CollectionPestDisease.class);
        collectionPestDiseaseRepository.save(model);
    }

    @RequestMapping("deleteById")
    public void deleteById(Integer id) {
        collectionPestDiseaseRepository.deleteById(id);
    }


    @RequestMapping("update")
    public void update(CollectionPestDisease collectionPestDisease) {
        collectionPestDiseaseRepository.save(collectionPestDisease);
    }

    @RequestMapping("findById")
    public CollectionPestDisease findById(Integer id) {
        return collectionPestDiseaseRepository.findById(id).get();
    }

    @RequestMapping("findByUserId")
    public List<CollectionPestDisease> findByUserId(Integer id) {
        return collectionPestDiseaseRepository.findByUserId(id);
    }

    @RequestMapping("deleteAll")
    public void deleteAll(String list) {
        List<CollectionPestDisease> collectionPestDiseases = JSON.parseArray(list, CollectionPestDisease.class);
        collectionPestDiseaseRepository.deleteInBatch(collectionPestDiseases);
    }
    @RequestMapping("deleteByPestDiseaseIdIn")
    public void deleteByPestDiseaseIdIn(String ids) {
        List<Integer> integers = JSON.parseArray(ids, Integer.class);
        collectionPestDiseaseRepository.batchDeleteIds(integers);
    }
    @RequestMapping("findByUserIdAndName")
    public CollectionPestDisease findByUserIdAndName(Integer userId, String name) {
        List<CollectionPestDisease> collectionPestDiseases = collectionPestDiseaseRepository.findByUserIdAndName(userId,name);
        if (!collectionPestDiseases.isEmpty()){
            return collectionPestDiseases.get(0);
        }else {
            return null;
        }
    }
    @RequestMapping("findIsCollectionByUserIdAndName")
    public Map<String, Object> findIsCollectionByUserIdAndName(Integer userId, String name) {
        CollectionPestDisease collectionPestDisease = findByUserIdAndName(userId,name);
        Map<String, Object> r = new HashMap<>();
        if (collectionPestDisease ==null ) {
            r.put("flag", 1);
        } else {
            r.put("flag", 0);
        }
        return r;
    }
    @RequestMapping("deleteByUserIdAndName")
    public void deleteByUserIdAndName(Integer userId, String name) {
        collectionPestDiseaseRepository.deleteByUserIdAndName(userId,name);
    }

}
