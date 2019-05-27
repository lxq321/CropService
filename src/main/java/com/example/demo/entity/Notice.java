package com.example.demo.entity;

import javax.persistence.*;

/**
 * @author 练续强
 * @Description TODO(通知表)@专家
 * @Date Create in 21:24 2019/4/18
 * @Modified By:
 */

@Entity
@Table(name = "C_NOTICE")
public class Notice {
    /**
     * 唯一不重复
     * 声明主键
     * 声明主键的生成策略
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluation;
    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Expert expert;
    private Integer isRead;//是否已读（0未读，1已读）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}
