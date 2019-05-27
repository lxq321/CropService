package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Jpa.CollectionNewsRepository;
import com.example.demo.entity.CollectionNews;
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
@RequestMapping("/api/collectionNews")
public class CollectionNewsController {

    @Autowired
    private CollectionNewsRepository collectionNewsRepository;

    @RequestMapping("list")
    public List<CollectionNews> list() {
        return collectionNewsRepository.findAll();
    }

    @RequestMapping("save")
    public void save(String collectionNews) {
        CollectionNews model = JSON.parseObject(collectionNews, CollectionNews.class);
        collectionNewsRepository.save(model);
    }

    @RequestMapping("deleteById")
    public void deleteById(Integer id) {
        collectionNewsRepository.deleteById(id);
    }


    @RequestMapping("update")
    public void update(CollectionNews collectionNews) {
        collectionNewsRepository.save(collectionNews);
    }

    @RequestMapping("findById")
    public CollectionNews findById(Integer id) {
        return collectionNewsRepository.findById(id).get();
    }

    @RequestMapping("findByUserId")
    public List<CollectionNews> findByUserId(Integer id) {
        return collectionNewsRepository.findByUserId(id);
    }

    @RequestMapping("deleteAll")
    public void deleteAll(String list) {
        List<CollectionNews> collectionNewss = JSON.parseArray(list, CollectionNews.class);
        collectionNewsRepository.deleteInBatch(collectionNewss);
    }
    @RequestMapping("deleteByNewsIdIn")
    public void deleteByNewsIdIn(String ids) {
        List<Integer> integers = JSON.parseArray(ids, Integer.class);
        collectionNewsRepository.batchDeleteIds(integers);
    }
    @RequestMapping("findByUserIdAndLinkUrl")
    public CollectionNews findByUserIdAndLinkUrl(Integer userId, String linkUrl) {
        List<CollectionNews> collectionNewss = collectionNewsRepository.findByUserIdAndLinkUrl(userId,linkUrl);
        if (!collectionNewss.isEmpty()){
            return collectionNewss.get(0);
        }else {
            return null;
        }
    }
    @RequestMapping("findIsCollectionByUserIdAndLinkUrl")
    public Map<String, Object> findIsCollectionByUserIdLinkUrl(Integer userId, String linkUrl) {
        CollectionNews collectionNews = findByUserIdAndLinkUrl(userId,linkUrl);
        Map<String, Object> r = new HashMap<>();
        if (collectionNews ==null ) {
            r.put("flag", 1);
        } else {
            r.put("flag", 0);
        }
        return r;
    }
    @RequestMapping("deleteByUserIdAndLinkUrl")
    public void deleteByUserIdAndLinkUrl(Integer userId, String linkUrl) {
        collectionNewsRepository.deleteByUserIdAndLinkUrl(userId,linkUrl);
    }

}
