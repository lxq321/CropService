package com.example.demo.entity;

import javax.persistence.*;

/**
 * @author 练续强
 * @Description TODO(申请专家表)
 * @Date Create in 17:14 2019/4/15
 * @Modified By:
 */
@Entity
@Table(name = "C_EXPERT_Apply")
public class ExpertApply {
    /**
     * 唯一不重复
     * 声明主键
     * 声明主键的生成策略
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String headImage;//专家头像
    private String type;//申请专家类型（企业专家、种养专家）
    private String speciality;//专精领域
    private String realName;//真实姓名
    private String idCode;//证件号码
    private String employer;//工作单位
    private String jobTitle;//技术职称
    private String address;//通讯地址
    private String thumbImage;//资格证书
    private String info;//个人简介
    private Integer state;//审核状态（1审核中，2审核通过，3驳回）

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user; //角色ID

    public ExpertApply() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }


    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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

    public String getThumbImage() {
        return thumbImage;
    }

    public void setThumbImage(String thumbImage) {
        this.thumbImage = thumbImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
