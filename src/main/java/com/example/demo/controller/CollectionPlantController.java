package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Jpa.CollectionPlantRepository;
import com.example.demo.entity.CollectionPlant;
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
@RequestMapping("/api/collectionPlant")
public class CollectionPlantController {

    @Autowired
    private CollectionPlantRepository collectionPlantRepository;

    @RequestMapping("list")
    public List<CollectionPlant> list() {
        return collectionPlantRepository.findAll();
    }

    @RequestMapping("save")
    public void save(String collectionPlant) {
        CollectionPlant model = JSON.parseObject(collectionPlant, CollectionPlant.class);
        collectionPlantRepository.save(model);
    }

    @RequestMapping("deleteById")
    public void deleteById(Integer id) {
        collectionPlantRepository.deleteById(id);
    }


    @RequestMapping("update")
    public void update(CollectionPlant collectionPlant) {
        collectionPlantRepository.save(collectionPlant);
    }

    @RequestMapping("findById")
    public CollectionPlant findById(Integer id) {
        return collectionPlantRepository.findById(id).get();
    }

    @RequestMapping("findByUserId")
    public List<CollectionPlant> findByUserId(Integer id) {
        return collectionPlantRepository.findByUserId(id);
    }

    @RequestMapping("deleteAll")
    public void deleteAll(String list) {
        List<CollectionPlant> collectionPlants = JSON.parseArray(list, CollectionPlant.class);
        collectionPlantRepository.deleteInBatch(collectionPlants);
    }
    @RequestMapping("deleteByPlantIdIn")
    public void deleteByPlantIdIn(String ids) {
        List<Integer> integers = JSON.parseArray(ids, Integer.class);
        collectionPlantRepository.batchDeleteIds(integers);
    }
    @RequestMapping("findByUserIdAndLinkUrl")
    public CollectionPlant findByUserIdAndLinkUrl(Integer userId, String linkUrl) {
        List<CollectionPlant> collectionPlants = collectionPlantRepository.findByUserIdAndLinkUrl(userId,linkUrl);
        if (!collectionPlants.isEmpty()){
            return collectionPlants.get(0);
        }else {
            return null;
        }
    }
    @RequestMapping("findIsCollectionByUserIdAndLinkUrl")
    public Map<String, Object> findIsCollectionByUserIdAndLinkUrl(Integer userId, String linkUrl) {
        CollectionPlant collectionPlant = findByUserIdAndLinkUrl(userId,linkUrl);
        Map<String, Object> r = new HashMap<>();
        if (collectionPlant ==null ) {
            r.put("flag", 1);
        } else {
            r.put("flag", 0);
        }
        return r;
    }
    @RequestMapping("deleteByUserIdAndLinkUrl")
    public void deleteByUserIdAndLinkUrl(Integer userId, String linkUrl) {
        collectionPlantRepository.deleteByUserIdAndLinkUrl(userId,linkUrl);
    }

}
