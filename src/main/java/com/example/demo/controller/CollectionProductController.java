package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Jpa.CollectionProductRepository;
import com.example.demo.entity.CollectionProduct;
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
@RequestMapping("/api/collectionProduct")
public class CollectionProductController {

    @Autowired
    private CollectionProductRepository collectionProductRepository;

    @RequestMapping("list")
    public List<CollectionProduct> list() {
        return collectionProductRepository.findAll();
    }

    @RequestMapping("save")
    public void save(String collectionProduct) {
        CollectionProduct model = JSON.parseObject(collectionProduct, CollectionProduct.class);
        collectionProductRepository.save(model);
    }

    @RequestMapping("deleteById")
    public void deleteById(Integer id) {
        collectionProductRepository.deleteById(id);
    }

    @RequestMapping("update")
    public void update(CollectionProduct collectionProduct) {
        collectionProductRepository.save(collectionProduct);
    }

    @RequestMapping("findById")
    public CollectionProduct findById(Integer id) {
        return collectionProductRepository.findById(id).get();
    }

    @RequestMapping("deleteByProductIdIn")
    public void deleteByProductIdIn(String ids) {
        List<Integer> integers = JSON.parseArray(ids, Integer.class);
        collectionProductRepository.batchDeleteUser(integers);
    }

    @RequestMapping("findByUserId")
    public List<CollectionProduct> findByUserId(Integer id) {
        return collectionProductRepository.findByUserId(id);
    }

    @RequestMapping("findByProductId")
    public List<CollectionProduct> findByProductId(Integer id) {
        return collectionProductRepository.findByProductId(id);
    }


    @RequestMapping("findByUserIdAndProductId")
    public CollectionProduct findByUserIdAndProductId(Integer userId, Integer productId) {
        List<CollectionProduct> collectionProducts = collectionProductRepository.findByUserIdAndProductId(userId,productId);
        if (!collectionProducts.isEmpty()){
            return collectionProducts.get(0);
        }else {
            return null;
        }
    }
    @RequestMapping("findIsCollectionByUserIdAndProductId")
    public Map<String, Object> findIsCollectionByUserIdAndProductId(Integer userId,Integer productId) {
        CollectionProduct collectionProduct = findByUserIdAndProductId(userId,productId);
        Map<String, Object> r = new HashMap<>();
        if (collectionProduct ==null ) {
            r.put("flag", 1);
        } else {
            r.put("flag", 0);
        }
        return r;
    }

    @RequestMapping("deleteByUserIdAndProductId")
    public void deleteByUserIdAndProductId(Integer userId,Integer productId) {
        collectionProductRepository.deleteByUserIdAndProductId(userId,productId);
    }


    /*  @RequestMapping("findAnswerQuestionsByProductId")
    public void findByExample(String id, String currentPage, String pagesize, String sort) {
        Product product = new Product();
        User user=new User();
        Role role=new Role();
        user.setRole(role);
        product.setUser(user);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());

        //创建实例
        Example<Product> example = Example.of(product, matcher);

        //分页
        Pageable pageable = MyUtil.getPageable(currentPage, pagesize, Sort.Direction.DESC, sort);
         collectionProductRepository.findAll(example, pageable).getContent();
    }*/

}
