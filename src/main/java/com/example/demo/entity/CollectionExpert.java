package com.example.demo.entity;

/**
 * @author 练续强
 * @Description TODO(收藏专家)
 * @Date Create in 9:30 2019/4/17
 * @Modified By:
 */

import javax.persistence.*;

@Entity
@Table(name = "c_collection_Expert")
public class CollectionExpert {
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
    @JoinColumn(name = "expert_id")
    private Expert expert;

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

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }
}
