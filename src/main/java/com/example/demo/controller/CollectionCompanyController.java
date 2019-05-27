package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Jpa.CollectionCompanyRepository;
import com.example.demo.entity.CollectionCompany;
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
@RequestMapping("/api/collectionCompany")
public class CollectionCompanyController {

    @Autowired
    private CollectionCompanyRepository collectionCompanyRepository;

    @RequestMapping("list")
    public List<CollectionCompany> list() {
        return collectionCompanyRepository.findAll();
    }

    @RequestMapping("save")
    public void save(String collectionCompany) {
        CollectionCompany model = JSON.parseObject(collectionCompany, CollectionCompany.class);
        collectionCompanyRepository.save(model);
    }

    @RequestMapping("deleteById")
    public void deleteById(Integer id) {
        collectionCompanyRepository.deleteById(id);
    }

    @RequestMapping("update")
    public void update(CollectionCompany collectionCompany) {
        collectionCompanyRepository.save(collectionCompany);
    }

    @RequestMapping("findById")
    public CollectionCompany findById(Integer id) {
        return collectionCompanyRepository.findById(id).get();
    }

    @RequestMapping("deleteByCompanyIdIn")
    public void deleteByCompanyIdIn(String ids) {
        List<Integer> integers = JSON.parseArray(ids, Integer.class);
        collectionCompanyRepository.batchDeleteUser(integers);
    }

    @RequestMapping("findByUserId")
    public List<CollectionCompany> findByUserId(Integer id) {
        return collectionCompanyRepository.findByUserId(id);
    }

    @RequestMapping("findByCompanyId")
    public List<CollectionCompany> findByCompanyId(Integer id) {
        return collectionCompanyRepository.findByCompanyId(id);
    }


    @RequestMapping("findByUserIdAndCompanyId")
    public CollectionCompany findByUserIdAndCompanyId(Integer userId, Integer companyId) {
        List<CollectionCompany> collectionCompanys = collectionCompanyRepository.findByUserIdAndCompanyId(userId,companyId);
        if (!collectionCompanys.isEmpty()){
            return collectionCompanys.get(0);
        }else {
            return null;
        }
    }
    @RequestMapping("findIsCollectionByUserIdAndCompanyId")
    public Map<String, Object> findIsCollectionByUserIdAndCompanyId(Integer userId,Integer companyId) {
        CollectionCompany collectionCompany = findByUserIdAndCompanyId(userId,companyId);
        Map<String, Object> r = new HashMap<>();
        if (collectionCompany ==null ) {
            r.put("flag", 1);
        } else {
            r.put("flag", 0);
        }
        return r;
    }

    @RequestMapping("deleteByUserIdAndCompanyId")
    public void deleteByUserIdAndCompanyId(Integer userId,Integer companyId) {
        collectionCompanyRepository.deleteByUserIdAndCompanyId(userId,companyId);
    }


    /*  @RequestMapping("findAnswerQuestionsByCompanyId")
    public void findByExample(String id, String currentPage, String pagesize, String sort) {
        Company company = new Company();
        User user=new User();
        Role role=new Role();
        user.setRole(role);
        company.setUser(user);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());

        //创建实例
        Example<Company> example = Example.of(company, matcher);

        //分页
        Pageable pageable = MyUtil.getPageable(currentPage, pagesize, Sort.Direction.DESC, sort);
         collectionCompanyRepository.findAll(example, pageable).getContent();
    }*/

}
