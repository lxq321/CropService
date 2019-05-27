package com.example.demo.entity;

import javax.persistence.*;

/**
 * @author 练续强
 * @Description TODO(专家点赞表)
 * @Date Create in 21:35 2019/5/20
 * @Modified By:
 */
@Entity
@Table(name = "C_Expert_Good")
public class ExpertGood {
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
