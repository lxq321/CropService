package com.example.demo.entity;

/**
 * @author 练续强
 * @Description TODO(收藏资讯)
 * @Date Create in 9:30 2019/4/17
 * @Modified By:
 */

import javax.persistence.*;

@Entity
@Table(name = "c_collection_News")
public class CollectionNews {
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

    private String title;//标题
    private String linkUrl;//链接地址

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
