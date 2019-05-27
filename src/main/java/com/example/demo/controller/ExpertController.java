package com.example.demo.controller;

import com.example.demo.Jpa.ExpertRepository;
import com.example.demo.entity.Expert;
import com.example.demo.entity.User;
import com.example.demo.entity.Role;
import com.example.demo.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/api/expert")
public class ExpertController {

    @Autowired
    private ExpertRepository expertRepository;

    @RequestMapping("list")
    public List<Expert> list() {
        return expertRepository.findAll();
    }

    @RequestMapping("save")
    public void save(Expert expert) {
      /* Expert expert =new Expert();
       expert.setTitle("企业专家");*/
        expertRepository.save(expert);
    }

    @RequestMapping("deleteById")
    public void deleteById(int id) {
        expertRepository.deleteById(id);
    }

    @RequestMapping("update")
    public void update(Expert expert) {
        expertRepository.save(expert);
    }

    @RequestMapping("findById")
    public Expert findById(Integer id) {
        return expertRepository.findById(id).get();
    }

    @RequestMapping("findByUserId")
    public Expert findByUserId(Integer id) {
        return expertRepository.findByUserId(id).get(0);
    }

    @RequestMapping("findByExample")
    public List<Expert> findByExample(String speciality,String expertName, Integer currentPage, Integer pagesize, String sort) {
        Expert expert = new Expert();
        expert.setSpeciality(speciality);
        User user =new User();
        Role role=new Role();
        role.setName(expertName);
        user.setRole(role);
        expert.setUser(user);
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withMatcher("speciality", ExampleMatcher.GenericPropertyMatchers.contains()) //地址采用“开始匹配”的方式查询
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains()); //地址采用“开始匹配”的方式查询

        //创建实例
        Example<Expert> example = Example.of(expert, matcher);

        //分页
        Pageable pageable = MyUtil.getPageable(currentPage, pagesize, Sort.Direction.DESC, sort);
        return expertRepository.findAll(example, pageable).getContent();
    }
    @PostMapping("/findByUserNameLike")
    public List<Expert> findByUserNameLike(String name){
        return expertRepository.findByUser_NameLike("%"+name+"%");
    }

}
