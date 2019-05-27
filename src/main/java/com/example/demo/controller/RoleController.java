package com.example.demo.controller;

import com.example.demo.Jpa.RoleRepository;
import com.example.demo.entity.Role;
import com.example.demo.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/api/role")
public class RoleController {


    @Autowired
    private RoleRepository roleRepository;

 /*   @RequestMapping("list")
    public List<Role> list(){
        return roleRepository.findAll();
    }*/
    @RequestMapping("listPage")
    public List<Role> list(Integer currentPage,Integer pagesize){

        Pageable pageable = MyUtil.getPageable(currentPage, pagesize, Sort.Direction.DESC, "id");
        Page<Role> models = roleRepository.findAll(pageable);
        return models.getContent();
    }
    @RequestMapping("save")
    public void save(Role role){
      /* Role role =new Role();
       role.setTitle("企业专家");*/
       roleRepository.save(role);
    }
    @RequestMapping("deleteById")
    public void deleteById(Integer id){
       roleRepository.deleteById(id);
    }
    @RequestMapping("update")
    public void update(Role role){
        roleRepository.save(role);
    }
    @RequestMapping("findById")
    public Role findById(Integer id) {
        return roleRepository.findById(id).get();
    }
}
