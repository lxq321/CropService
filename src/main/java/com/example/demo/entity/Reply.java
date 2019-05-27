package com.example.demo.entity;

/**
 * @author 练续强
 * @Description TODO(评论)
 * @Date Create in 9:30 2019/4/17
 * @Modified By:
 */

import javax.persistence.*;

@Entity
@Table(name = "C_REPLY")
public class Reply {
    /**
     * 唯一不重复
     * 声明主键
     * 声明主键的生成策略
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String createTime;//评论时间
    private String content;//内容
    private Integer zanCount;//点赞数
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;//评论用户
    @ManyToOne
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluation;//帖子

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getZanCount() {
        return zanCount;
    }

    public void setZanCount(Integer zanCount) {
        this.zanCount = zanCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
}
