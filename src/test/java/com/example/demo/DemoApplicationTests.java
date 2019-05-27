package com.example.demo;

import com.example.demo.Jpa.RoleRepository;
import com.example.demo.Jpa.UserRepository;
import com.example.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Test
    public void contextLoads() {
    }
    @Value("${localhost.images}")
    String imagesPath;
    @Test
    @Transactional
//    @rollbask(false)

    public void save(){
        User user = userRepository.findById(1).get();
//        User user= userRepository.findOne(1);
       /* User user=new User();
        user.setTitle("xiao1");
        user.setMobile("123456");
        Role role=new Role();
        role.setTitle("专家");
        user.setRole(role);
        userRepository.save(user);*/
      /* Role role=roleRepository.findById(4).get();
        System.out.println(role.getTitle());*/
//       role.setId(4);
    /*   role.setTitle("专家1");
        Set<User> members=role.getUsers();
        User user=new User();
        user.setTitle("dfslhl");

        members.clear();
        members.add(user);
        role.setUsers(members);
        roleRepository.save(role);*/
        System.out.println(imagesPath+ File.separator+ "123.png");
    }

}
