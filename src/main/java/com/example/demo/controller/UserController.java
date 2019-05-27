package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import com.example.demo.Jpa.RoleRepository;
import com.example.demo.Jpa.UserRepository;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 练续强
 * @Description TODO()
 * @Date Create in 20:53 2019/4/15
 * @Modified By:
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    SMSService smsService;

    @Autowired
    StringRedisTemplate redisTemplate;


    @RequestMapping("list")
    public List<User> list() {
        return userRepository.findByRoleId(1);
    }


    @RequestMapping("save")
    public void save(User user) {
       /* User user=new User();
        user.setTitle("xiao");
        user.setMobile("123456");
        Role role = roleRepository.getOne(9);
        user.setRole(role);*/
        userRepository.save(user);
    }

    @RequestMapping("deleteById")
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @RequestMapping("update")
    public void update(User user) {
        userRepository.save(user);
    }

    @RequestMapping("findById")
    public User findById(Integer id) {
        return userRepository.findById(id).get();
    }

    @RequestMapping("findByRoleId")
    public List<User> findByRoleId(Integer id) {
        return userRepository.findByRoleId(id);
    }
    @RequestMapping("findByMobile")
    public User findByMobile(String mobile) {
        List<User> users = userRepository.findByMobile(mobile);
        if (!users.isEmpty()){
            return users.get(0);
        }else {
            return null;
        }
    }

    /**
     * 登陆
     *
     * @param mobile   手机号
     * @param password 密码
     * @return Map<String  , Object>  登陆成功或失败的键值对
     */
    @PostMapping("/login")
    @CrossOrigin("*")
    public Map<String, Object> login(String mobile, String password) {
        Map<String, Object> r = new HashMap<>();
        User user =findByMobile(mobile);
        if (user ==null || !user.getPassword().equals(password)) {
            r.put("msg", "用户名或密码错误");
            r.put("flag", 1);
        } else {
            r.put("msg", "登陆成功");
            r.put("flag", 0);
            r.put("user", JSON.toJSONString(user));
        }
        return r;
    }

    /**
     * 注册方法
     * @param mobile   手机号
     * @param password 密码
     * @param formCode 验证码
     * @param token    UUII值
     * @return Map<String  ,   Object>  注册成功或失败的键值对
     */
    @PostMapping("/register")
    public Map<String, Object> register(String name,String mobile, String password,
                                        String formCode, String token) {
        Map<String, Object> r = new HashMap<>();
        //从redis获得对应的registerCode
        String registerCode = redisTemplate.opsForValue().get(token + mobile);
        //判断registerCode是否存在，是否正确
        if (registerCode == null || !registerCode.equals(formCode)) {
            r.put("msg", "校验码过期或输入错误");
            r.put("flag", 1);
        } else {
            //判断手机号是否已经注册
            User byMobile = findByMobile(mobile);
            if (byMobile != null) {
                r.put("msg", "该手机号已注册");
                r.put("flag", 1);
            } else {
                User user = new User();
                user.setMobile(mobile);
                user.setPassword(password);
                user.setName(name);
                user.setHeadImage("/images/timg.jpg");
                Role role=roleRepository.findById(1).get();
                user.setRole(role);
                save(user);
                r.put("msg", "注册成功");
                r.put("flag", 0);
            }
        }
        return r;
    }

    /**
     * 忘记密码，进行密码修改
     *
     * @param mobile      手机号
     * @param newPassword 新密码
     * @param formCode    验证码
     * @param token       UUID值
     * @return Map<String               ,       Object>  密码修改成功或失败的键值对
     */
    @PostMapping("/forgetPassword")
    public Map<String, Object> forgetPassword(String mobile, String newPassword, String formCode, String token) {

        Map<String, Object> r = new HashMap<>();
        redisTemplate.opsForValue().get(token + mobile);
        //从redis获得对应的registerCode
        String registerCode = redisTemplate.opsForValue().get(token + mobile);

        //判断registerCode是否存在，是否正确
        if (registerCode == null || !registerCode.equals(formCode)) {
            r.put("msg", "校验码过期或输入错误");
            r.put("flag", 1);
        } else {
            User user = findByMobile(mobile);
            user.setPassword(newPassword);
            update(user);
            r.put("msg", "修改成功");
            r.put("flag", 0);
        }
        /*  Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        //生成一个[0,9]整数
        for (int i = 0; i < 5; i++) {
            //System.out.println( r.nextInt(10));
            buffer.append(random.nextInt(10));
        }
        String code = buffer.toString();*/
        return r;
    }

    /**
     * 获取验证码
     * @param mobile 手机号
     * @return Map<String , Object>  获得验证码成功或失败的键值对
     * @throws ClientException
     */
    @PostMapping("/registerCode")
    @CrossOrigin("*")  //跨域
    public Map<String, Object> registerCode(String mobile) throws ClientException {
        String code = smsService.getCode(mobile);//获取验证码
        System.out.println(code);
        String token = UUID.randomUUID().toString();//获取UUID唯一值
        //临时存储在redis，保持时间60s
        redisTemplate.opsForValue().set(token + mobile, code);
        redisTemplate.expire(token + mobile, 60, TimeUnit.SECONDS);

        Map<String, Object> r = new HashMap<>();
        r.put("msg", "发送校验码成功");
        r.put("code", 0);
        r.put("token", token);
        return r;
    }

    /**
     * 修改密码
     * @param mobile 手机号
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return Map<String , Object>  密码修改成功或失败的键值对
     */
    @PostMapping("/updatePassword")
    @CrossOrigin("*")
    public Map<String,Object> updatePassword(String mobile,String oldPassword,String newPassword){
        Map<String, Object> r=new HashMap<>();
        User user = findByMobile(mobile);
        //判断密码是否正确
        if (user !=null&& user.getPassword().equals(oldPassword)){
            user.setPassword(newPassword);
            update(user);
            r.put("msg", "修改成功，请重新登陆");
            r.put("flag", 0);
        }else {
            r.put("msg", "修改失败，密码错误");
            r.put("flag", 1);
        }
        return r;
    }
    @PostMapping("updateUser")
    public void save(String user){
        User newUser = JSON.parseObject(user, User.class);
        User oldUser = userRepository.findById(newUser.getId()).get();
        oldUser.setHeadImage(newUser.getHeadImage());
        oldUser.setName(newUser.getName());
        oldUser.setAddress(newUser.getAddress());
        oldUser.setInfo(newUser.getInfo());
        oldUser.setSex(newUser.getSex());
        update(oldUser);
    }
}
