package com.example.demo.entity;

/**
 * @author 练续强
 * @Description TODO(收藏问题)
 * @Date Create in 9:30 2019/4/17
 * @Modified By:
 */

import javax.persistence.*;

@Entity
@Table(name = "c_collection_Evaluation")
public class CollectionEvaluation {
    /**
     * 唯一不重复
     * 声明主键
     * 声明主键的生成策略
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
