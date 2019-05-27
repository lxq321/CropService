package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * @author 练续强
 * @Description TODO(专家表)
 * @Date Create in 17:14 2019/4/15
 * @Modified By:
 */
@Entity
@Table(name = "C_EXPERT")
@JsonIgnoreProperties(ignoreUnknown = true, value =
        {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Expert {
    /**
     * 唯一不重复
     * 声明主键
     * 声明主键的生成策略
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    private String headImage;//头像
    private String speciality;//擅长
    private String address;//所在地
    private String employer;//单位
    private String jobTitle;//职称
    private String info;
    private Integer replyCount;//回复数
    private Integer attentionCount;//关注数
    private Integer adoptionCount;//采纳数
    private Integer likeCount;//点赞数

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    @JsonIgnoreProperties(ignoreUnknown = true, value = {"expert"})
    private User user; //角色ID

    public Expert() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

/*    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }*/

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public Integer getAdoptionCount() {
        return adoptionCount;
    }

    public void setAdoptionCount(Integer adoptionCount) {
        this.adoptionCount = adoptionCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(Integer attentionCount) {
        this.attentionCount = attentionCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
}
