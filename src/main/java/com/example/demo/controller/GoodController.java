package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Jpa.GoodRepository;
import com.example.demo.Jpa.ReplyRepository;
import com.example.demo.entity.Good;
import com.example.demo.entity.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 练续强
 * @Description TODO()
 * @Date Create in 14:12 2019/5/7
 * @Modified By:
 */
@RestController
@RequestMapping("/api/good")
public class GoodController {
    @Autowired
    private GoodRepository goodRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @PostMapping("save")
    public void save(String good) {
        Good entity = JSON.parseObject(good, Good.class);
        //添加点赞
        Good save = goodRepository.save(entity);
        //获得评论实体
        Reply reply = replyRepository.findById(entity.getReply().getId()).get();
        //点赞数加一
        reply.setZanCount(reply.getZanCount()+1);
        replyRepository.save(reply);
    }
}
