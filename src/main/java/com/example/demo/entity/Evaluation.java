package com.example.demo.entity;

/**
 * @author 练续强
 * @Description TODO(帖子)
 * @Date Create in 9:30 2019/4/17
 * @Modified By:
 */

import javax.persistence.*;

@Entity
@Table(name = "C_EVALUATION")
public class Evaluation {
    /**
     * 唯一不重复
     * 声明主键
     * 声明主键的生成策略
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //帖子id
    private String content;//内容
    private String imageUrls;//帖子图片
    private Integer scan;//浏览数量
    private Integer commentCount;//回复数量
    private String cropName;//作物名称
    private String createTime;//发帖时间
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;//发帖人

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    public Integer getScan() {
        return scan;
    }

    public void setScan(Integer scan) {
        this.scan = scan;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
