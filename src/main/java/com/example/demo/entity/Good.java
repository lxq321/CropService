package com.example.demo.entity;
import javax.persistence.*;

/**
 * @author 练续强
 * @Description TODO(解答评论点赞表)
 * @Date Create in 14:06 2019/5/7
 * @Modified By:
 */
@Entity
@Table(name = "C_GOOD")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "reply_id")
    private Reply reply;

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

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }
}
