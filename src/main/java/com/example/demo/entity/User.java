package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * @author 练续强
 * @Description TODO(用户类)
 * @Date Create in 15:24 2019/1/18
 * @Modified By:
 */
@Entity
@Table(name = "C_USER")
@JsonIgnoreProperties(ignoreUnknown = true, value =
        {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class User {
    /**
     * 唯一不重复
     * 声明主键
     * 声明主键的生成策略
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mobile;
    private String password;
    private String name;
    private String headImage;//头像
    private String sex;
    private String address;
    private String info;

    //    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @ManyToOne
    @JoinColumn(name = "role_id")//指定当前实体外键
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"users"})
    private Role role; //角色ID

/*    @OneToOne
    @JoinColumn(name = "user")
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"user"})
    private Expert expert; // 专家集合*/

    public User() {
    }

    public User(String mobile, String password, String name, String headImage, Role role) {
        this.mobile = mobile;
        this.password = password;
        this.name = name;
        this.headImage = headImage;
        this.role = role;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

 /*   public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }*/
}
